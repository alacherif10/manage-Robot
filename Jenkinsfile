    pipeline {
    agent any
    tools {
        jdk "jdk17"
        maven "maven3"
    }
    environment {
        SCANNER_HOME = tool 'sonar-scanner'
        AWS_REGION = 'us-east-2'
        AWS_ACCOUNT_ID = '117459925946'  // Your AWS account ID from state file
        EKS_CLUSTER_NAME = 'eks-cluster'
        ECR_BACKEND_REPO = "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com/manage-robot-backend"
        ECR_FRONTEND_REPO = "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com/manage-robot-frontend"
    }
    stages {
        stage('Git Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/alacherif10/manage-Robot'
            }
        }
        stage('Compile') {
            steps {
                dir('backend') {
                    sh 'mvn clean compile'
                }
            }
        }
        stage('SonarQube Analysis') {
            steps {
                dir('backend') {
                    sh """
                        $SCANNER_HOME/bin/sonar-scanner \
                        -Dsonar.projectKey=manageRobot \
                        -Dsonar.projectName=manage-Robot \
                        -Dsonar.sources=. \
                        -Dsonar.java.binaries=target \
                        -Dsonar.host.url=http://3.141.192.10:9000 \
                        -Dsonar.login=squ_adddeebdada6cdceddbaaaee24b6de044e759a64
                    """
                }
            }
        }
        stage('Build Application') {
            steps {
                dir('backend') {
                    sh "mvn clean install"
                }
            }
        }
        stage('Docker Image Scan') {
            steps {
                sh "trivy image --format table -o trivy-backend-report.html ${ECR_BACKEND_REPO}:latest || true"
                sh "trivy image --format table -o trivy-frontend-report.html ${ECR_FRONTEND_REPO}:latest || true"
            }
        }
        stage('Build and Push Docker Images') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'aws-access-key-id', variable: 'AWS_ACCESS_KEY_ID'),
                                     string(credentialsId: 'aws-secret-access-key', variable: 'AWS_SECRET_ACCESS_KEY')]) {
                        sh '''
                            # Login to ECR
                            aws ecr get-login-password --region ${AWS_REGION} | docker login --username AWS --password-stdin ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com
                            
                            # Build Docker images
                            docker build -t manage-robot-backend:latest ./backend
                            docker build -t manage-robot-frontend:latest ./frontend
                            
                            # Tag images for ECR
                            docker tag manage-robot-backend:latest ${ECR_BACKEND_REPO}:${BUILD_NUMBER}
                            docker tag manage-robot-backend:latest ${ECR_BACKEND_REPO}:latest
                            docker tag manage-robot-frontend:latest ${ECR_FRONTEND_REPO}:${BUILD_NUMBER}
                            docker tag manage-robot-frontend:latest ${ECR_FRONTEND_REPO}:latest
                            
                            # Push to ECR
                            docker push ${ECR_BACKEND_REPO}:${BUILD_NUMBER}
                            docker push ${ECR_BACKEND_REPO}:latest
                            docker push ${ECR_FRONTEND_REPO}:${BUILD_NUMBER}
                            docker push ${ECR_FRONTEND_REPO}:latest
                        '''
                    }
                }
            }
        }
        stage('Deploy to Kubernetes') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'aws-access-key-id', variable: 'AWS_ACCESS_KEY_ID'),
                                     string(credentialsId: 'aws-secret-access-key', variable: 'AWS_SECRET_ACCESS_KEY')]) {
                        sh '''
                            # Configure kubectl for EKS
                            aws eks update-kubeconfig --name ${EKS_CLUSTER_NAME} --region ${AWS_REGION}
                            
                            # Update image tags in deployment files
                            sed -i "s|TAG_VERSION|${BUILD_NUMBER}|g" infra/k8s/backend-deployment.yaml
                            sed -i "s|TAG_VERSION|${BUILD_NUMBER}|g" infra/k8s/frontend-deployment.yaml
                            
                            # Update ECR repository URLs in deployment files
                            sed -i "s|us-docker.pkg.dev/manage-robot/backend-repo/myapp-backend|${ECR_BACKEND_REPO}|g" infra/k8s/backend-deployment.yaml
                            sed -i "s|us-docker.pkg.dev/manage-robot/frontend-repo/myapp-frontend|${ECR_FRONTEND_REPO}|g" infra/k8s/frontend-deployment.yaml
                            
                            # Deploy MongoDB first (database should be up before backend)
                            echo "=== Deploying MongoDB ==="
                            kubectl apply -f infra/k8s/mongodb-deployment.yaml
                            kubectl apply -f infra/k8s/mongodb-service.yaml
                            
                            # Deploy services
                            echo "=== Deploying Services ==="
                            kubectl apply -f infra/k8s/backend-service.yaml
                            kubectl apply -f infra/k8s/frontend-service.yaml
                            
                            # Deploy applications
                            echo "=== Deploying Applications ==="
                            kubectl apply -f infra/k8s/backend-deployment.yaml
                            kubectl apply -f infra/k8s/frontend-deployment.yaml
                            
                            # Wait for MongoDB to be ready
                            echo "=== Waiting for MongoDB ==="
                            kubectl rollout status deployment/mongodb-deployment --timeout=5m
                            
                            # Wait for application deployments
                            echo "=== Waiting for Backend ==="
                            kubectl rollout status deployment/backend-deployment --timeout=10m
                            
                            echo "=== Waiting for Frontend ==="
                            kubectl rollout status deployment/frontend-deployment --timeout=10m
                            
                            # Display final status
                            echo "=== Pods ==="
                            kubectl get pods
                            echo "=== Services ==="
                            kubectl get services
                        '''
                    }
                }
            }
        }
    }
    post {
        always {
            script {
                def jobName = env.JOB_NAME
                def buildNumber = env.BUILD_NUMBER
                def pipelineStatus = currentBuild.result ?: 'UNKNOWN'
                def bannerColor = pipelineStatus.toUpperCase() == 'SUCCESS' ? 'green' : 'red'

                def body = """
                    <html>
                    <body>
                    <div style="border: 4px solid ${bannerColor}; padding: 10px;">
                    <h2>${jobName} - Build ${buildNumber}</h2>
                    <div style="background-color: ${bannerColor}; padding: 10px;">
                    <h3 style="color: white;">Pipeline Status: ${pipelineStatus.toUpperCase()}</h3>
                    </div>
                    <p>Check the <a href="${BUILD_URL}">console output</a>.</p>
                    </div>
                    </body>
                    </html>
                """

                emailext (
                    subject: "${jobName} - Build ${buildNumber} - ${pipelineStatus.toUpperCase()}",
                    body: body,
                    to: 'cherifala10@gmail.com',
                    from: 'jenkins@example.com',
                    replyTo: 'jenkins@example.com',
                    mimeType: 'text/html',
                    attachmentsPattern: 'trivy-*-report.html'
                )
            }
        }
    }
}
