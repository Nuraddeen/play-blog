package controllers

import javax.inject._
import play.api._
import play.api.mvc._

import forms.CreateUserForm

@Singleton
class PeopleController @Inject() (
    cc: ControllerComponents,
    components: MessagesControllerComponents,
    messagesAction: MessagesActionBuilder
) extends MessagesAbstractController(components)
    // with play.api.i18n.I18nSupport
    {

  import play.api.data.Form
  import play.api.data.Forms._

  def show(name: String, age: Int) = Action {
    implicit request: Request[AnyContent] =>
      Ok(views.html.users.show(name, age))
  }

  def create() = Action { implicit request: MessagesRequest[AnyContent] =>
    // pre filled form
    //   Ok(views.html.users.create(CreateUserForm.form.fill(CreateUserForm("Nura fillde", new java.util.Date(), 8908))))

    Ok(views.html.users.create(CreateUserForm.form))
  }

  // def save() = Action { request =>
  //   val data = request.body.asFormUrlEncoded

  //   data.map { args =>
  //     val fullname = args("fullname").head
  //     val dob = args("dob").head

  //     Redirect(routes.PeopleController.create())
  //   }.getOrElse(Redirect(routes.PeopleController.create()))
  // }

  def save() = Action { implicit request =>
    CreateUserForm.form
      .bindFromRequest()
      .fold(
        formWithErrors => {
          BadRequest(views.html.users.create(formWithErrors))
        },
        formData => {
          val message = s"==========\n YOUR SUBMISSION " +
            s"\n========== Fullname: ${formData.fullname} " +
            s"\n DOB: ${formData.dob}" +
            s"\n Postal Code: ${formData.postalCode}"
          Ok(message)
        }
      )

  }

}
