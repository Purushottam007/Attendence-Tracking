package Entity

class Company {

     Integer companyId
     String companyName
     String companyAddress
     String companyMail
     String companyNumber
     String companyWebsite


    static hasMany=[employeedetail:Employee,
                    addresses:Address,
                    geozone:Geozone,
                    event:EventData]


    static constraints = {


        companyAddress(nullable: true,blank: true)
        companyMail(nullable: true,blank: false)
        companyNumber(nullable: true,blank:false)
        companyWebsite(nullable: true,blank: true)

    }
}
