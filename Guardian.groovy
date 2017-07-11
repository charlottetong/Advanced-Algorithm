package kungfuschool

class Guardian {

    String firstName, lastName, email, mobileNumber
    Address address

    static constraints = {
      email email:true
      firstName blank:false
      lastName blank:false
      address nullable: true
    }
}
