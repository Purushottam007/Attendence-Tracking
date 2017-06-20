package Entity

class Device {

    String deviceName
    Integer deviceId

    static belongsTo= [employee:Employee]
    static hasMany = [event:EventData]

    static constraints = {
    }
}
