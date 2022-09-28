package forms

import java.util.Date
import play.api.data.Form
import play.api.data._
import play.api.data.Forms._
import play.api.data.validation.Constraints._

case class CreateUserForm(fullname: String, dob: Date, postalCode: Int)

object CreateUserForm {
  val form: Form[CreateUserForm] = Form(
    mapping(
      "fullname" -> text,
      "dob" -> date,
      "postalCode" -> number
    )(CreateUserForm.apply)(CreateUserForm.unapply)
  )
}
