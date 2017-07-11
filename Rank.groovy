package kungfuschool

class Rank {

    String rankName, beltColor= "white", requirementDes
    static constraints = {
      beltColor inList: ["white", "blank", "red", "yellow"]

    }

    String toString() {
      rankName
    }
}
