package forms

import java.util.Date
import play.api.data.Form
import play.api.data._
import play.api.data.Forms._
import play.api.data.validation.Constraints._

case class CreateUserForm(fullname: String, dob: Date, postalCode: Int, email: String, employed: Boolean, cities: List[String])

object CreateUserForm {
  val form: Form[CreateUserForm] = Form(
    mapping(
      "fullname" -> nonEmptyText,
      "dob" -> date,
      "postalCode" -> number,
      "email" -> email,
      "employed" -> boolean,
      "cities" -> list(text)
    )(CreateUserForm.apply)(CreateUserForm.unapply)
  )
}
