package com.infosys.infyride.utilities;

public enum InfyRideConstants {


    INFYRIDE_EXCEPTIONMSG_GENERAL("infyride.exceptionmessage.general"),

    INFYRIDE_PICKUPTODROPLOCATION_NOT_FOUND("infyride.pickuptodroplocation.notfound"),

    INFYRIDE_RIDEID_NOT_FOUND("infyride.rideid.notfound"),

    INFYRIDE_UPDATE_RIDE_ALREADY_COMPLETED("infyride.update.ridestatuscompleted"),

    INFYRIDE_UPDATE_RIDE_ALREADY_CANCELLED("infyride.update.ridestatuscancelled"),

    INFYRIDE_OLDANDNEWPICKUPLOCATION_SAME("infyride.update.oldandnewpickuplocationsame"),

    INFYRIDE_CANCEL_RIDE_ALREADY_COMPLETED("infyride.cancel.ridestatuscompleted"),

    INFYRIDE_CANCEL_RIDE_ALREADY_CANCELLED("infyride.cancel.ridestatuscancelled"),

    INFYRIDE_GETESTIMATED_FARE_SUCCESS("infyride.getestimatedfare.success"),

    INFYRIDE_BOOKING_SUCCESS("infyride.booking.success"),

    INFYRIDE_UPDATE_SUCCESS1("infyride.update.success1"),

    INFYRIDE_UPDATE_SUCCESS2("infyride.update.success2"),

    INFYRIDE_CANCEL_SUCCESS("infyride.cancel.success");



    private final String value;

    private InfyRideConstants(String value){
        this.value=value;
    }

    @Override
    public String toString(){
        return this.value;
    }
}
