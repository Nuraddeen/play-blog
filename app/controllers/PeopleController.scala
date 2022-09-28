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
    val data: CreateUserForm = CreateUserForm.form.bindFromRequest().get
    val message = s"==========\n YOUR SUBMISSION " +
      s"\n========== Fullname: ${data.fullname} " +
      s"\n DOB: ${data.dob}" +
      s"\n Postal Code: ${data.postalCode}"
    Ok(message)
  }

}
