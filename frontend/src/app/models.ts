export interface User{
    id : string;
    firstname : string;
    lastname : string;
    email : string;
    password : string
}

export enum RobotStatus{
    IDLE,
    ACTIVE,
    CHARGING,
    OFFLINE

}

export interface Robot{
    id : string;
    status : RobotStatus;
    batteryLevel : number;
    waterLevel : number;
    isOn : boolean;
}

export interface Zone{
    id : string;
    name : string;
}

export interface Scheduler{
    id : string;
    robot : Robot;
    zone :  Zone;
    startTime : Date;
    endTime : Date;
}

export interface Point{
    id : string;
    x : number;
    y : number;
}

export enum MissionStatus{
    PENDING,
    IN_PROGRESS,
    COMPLETED,
    FAILED
}

export interface Mission{
    id : string;
    name : string;
    status : MissionStatus;
    points: Point[]; 
    robot : Robot;
    startTime : Date;
    endTime : Date;
}

