package kungfuschool

class Address {
    String street
    String city = "Windsor"
    String state = "Ontario"
    static constraints = {
      state inList: ["Ontario", "BritishColumnbia", "Alberta"]
    }
    String toString() {
      street +" "+" "+city+" "+state
    }
}
