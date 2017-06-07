package Entity

import java.sql.Time

class EventData {

    Integer companyId

    Time timeStamp
    String statusCode
    Double latitude
    Double longititude
    String gpsAge
    String geozoneIndex
    String geozoneId
    Time creationTime

    static belongsTo=[device:Device]

    static constraints = {

        statusCode(nullable: true,blank:true)
        latitude(nullable: true,blank:true)
        longititude(nullable: true,blank:true)
        gpsAge(nullable: true,blank:true)
        geozoneIndex(nullable: true,blank:true)
        geozoneId(nullable: true,blank:true)
        creationTime(nullable: true,blank:true)

    }
}
