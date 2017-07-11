package kungfuschool

class Guard {

    Student student
    Guardian guardian
    String relation = "Father"
    static constraints = {
      relation inList: ["Father", "Mother", "Other Legal Guardians"]
      student nullable: true
      guardian nullable: true
    }
}
