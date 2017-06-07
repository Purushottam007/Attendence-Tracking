package Entity

class Address {

    String city
    String state
    String country
    String location
    String zip


    static constraints = {

        city(nullable:true,blank:true)
        state(nullable:true,blank:true)
        country(nullable:true,blank:true)
        location(nullable:false,blank:false)
        zip(nullable:true,blank:true)

    }
}
