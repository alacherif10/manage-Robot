resource "google_container_cluster" "manage_robot_cluster" {
  name                = "manage-robot-cluster"
  location            = var.region
  enable_autopilot    = true
  deletion_protection = false

  ip_allocation_policy {}
}

# Jenkins VM (minimal, no startup scripts)
resource "google_compute_instance" "jenkins" {
  name         = "jenkins-vm"
  machine_type = "e2-standard-4"  
  zone         = var.zone

  boot_disk {
    initialize_params {
      image = "projects/ubuntu-os-cloud/global/images/family/ubuntu-2204-lts"
      size  = 30  # small disk to save cost
    }
  }

  network_interface {
    network       = "default"
    access_config {} # external IP
  }

  tags = ["jenkins-server"]  # needed for firewall
}

# Firewall to allow access
resource "google_compute_firewall" "jenkins_firewall" {
  name    = "jenkins-firewall"
  network = "default"

  allow {
    protocol = "tcp"
    ports    = ["8080", "22", "9000"]
  }

  source_ranges = ["0.0.0.0/0"]
  target_tags   = ["jenkins-server"]
}
