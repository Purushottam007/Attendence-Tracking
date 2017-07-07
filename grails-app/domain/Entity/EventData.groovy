package Entity

//import java.sql.Time
import com.user.geozoneService.GeogoneService

class EventData {
    def geogoneService

    Integer companyId

    String timeStamp
    String statusCode
    String latitude
    String longititude
    String gpsAge
    String geozoneIndex
    String geozoneId
    String creationTime

    static belongsTo=[device:Device]

    static constraints = {

        statusCode(nullable: true,blank:true)
        latitude(nullable: true,blank:true)
        longititude(nullable: true,blank:true)
        gpsAge(nullable: true,blank:true)
        geozoneIndex(nullable: true,blank:true)
        geozoneId(nullable: true,blank:true)
        creationTime(nullable: true,blank:true)
        timeStamp(nullable: true,blank:true)
        companyId(nullable: true,blank:true)

    }
    /*def beforeInsert(){
        EventData eventData=new EventData()
        eventData.latitude
        eventData.longititude
        Geozone geozone=new Geozone()
        geozone.id
        geozone.latitude1
        geozone.longitude1
        geozone.radious

    }

    def afterInsert(){
        geogoneService

        return "hello"

    }*/

}
