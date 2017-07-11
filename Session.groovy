package kungfuschool

class Session {

    String dayOfWeek, classroom
    Integer startHours, startMins, endHours, endMins
    Classes classes
    static constraints = {
    	dayOfWeek inList: ["Monday", "Tuesday", "Wednesday", "Thursday","Friday"]

    }

    static mapping = {
    }
}
