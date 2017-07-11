package kungfuschool

class RegisterStudentController {

	def registerStudentService

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		render(view:"index",  model:[studentList: Student.list(params), studentCount: Student.count()])
	}

	def create() {
		render(view: "create", model: [student: new Student(), address: new Address(), guardian: new Guardian(), guard: new Guard(), rankList: Rank.list()])
	}

	def save() {
		//get the beans on the page
		def student = new Student(params.student)
		def address = new Address(params.address)
		def guardian = new Guardian(params.guardian)
		def guard = new Guard(params.guard)
		def rank = Rank.get(params.int("rank.id"))
		student.validate()
		address.validate()
		guardian.validate()
		guard.validate()
		//process type converting errors
		if (student.hasErrors() || address.hasErrors() || guardian.hasErrors() || guard.hasErrors()) {

		//	respond student.errors, view:'create'
			render(view:"create", model:[student: student, address: address, guardian: guardian, guard: guard, rankList: Rank.list()])
            		return
		}

		def stuId = registerStudentService.save(address, student, guardian, guard, rank)
		def stu = Student.get(stuId)
		def guardList = Guard.findAllByStudent(stu)

		flash.message = message(code: 'default.created.message', args: [message(code: 'student.label', default: 'Student'), student.toString()])
		render(view:"show", model:[student: stu, guardList: guardList])

	}

        def show() {
            def student = Student.get(params.int("id"))
            def guardList = Guard.findAllByStudent(student)
            render(view:"show", model:[student: student, guardList: guardList])
        }
}
