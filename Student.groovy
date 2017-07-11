package kungfuschool

class Student {

    String studentNumber,firstName,lastName, email, mobileNumber
    Date birthDate,enrollDate
    Address address
    Rank rank

    static constraints = {
          studentNumber unique: true
          email email: true
          firstName blank: false
          lastName blank: false
          birthDate max: new Date()
	  address nullable: true
	  rank nullable: true
    }

    static mapping = {

    }

    String toString() {

      firstName+" "+lastName
    }
}
