package Entity

class Company {

    // Integer companyId
     String companyName
     String companyAddress
     String companyMail
     String companyNumber
     String companyWebsite
    Geozone   geozone

    static hasMany=[employeedetail:Employee,
                    addresses:Address,
                    event:EventData]
    //static hasOne=[geozone:Geozone]


    static constraints = {


        companyAddress(nullable: true,blank: true)
        companyMail(nullable: true,blank: false)
        companyNumber(nullable: true,blank:false)
        companyWebsite(nullable: true,blank: true)
        geozone(nullable: true,blank: true)

    }
}
