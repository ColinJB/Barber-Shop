import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout ="templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.all());
      model.put("clients", Client.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylist", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("stylistName");
      String number = request.queryParams("stylistNumber");
      Stylist newStylist = new Stylist(name, number);
      newStylist.save();
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylist/:stylist_id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("stylist", Stylist.find(Integer.parseInt(request.params(":stylist_id"))));
      model.put("clients", Client.all());
      model.put("template", "templates/stylistInfo.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylist/:stylist_id/client", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("clientName");
      String number = request.queryParams("clientNumber");
      int stylist_id = Integer.parseInt(request.params(":stylist_id"));
      Client newClient = new Client(name, number, stylist_id);
      newClient.save();
      model.put("stylist", Stylist.find(Integer.parseInt(request.params(":stylist_id"))));
      model.put("clients", Client.all());
      response.redirect(request.headers("Referer"));
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylist/:stylist_id/update", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":stylist_id")));
      String name = request.queryParams("stylistName");
      String number = request.queryParams("stylistNumber");
      stylist.update(name, number);
      model.put("stylist", Stylist.find(Integer.parseInt(request.params(":stylist_id"))));
      model.put("clients", Client.all());
      response.redirect(request.headers("Referer"));
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/client", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("clientName");
      String number = request.queryParams("clientNumber");
      int stylist_id = Integer.parseInt(request.queryParams("stylist_id"));
      System.out.println(stylist_id);
      Client newClient = new Client(name, number, stylist_id);
      newClient.save();
      if (stylist_id > 0) {
        model.put("stylist", Stylist.find(stylist_id));
        model.put("clients", Client.all());
        model.put("template", "templates/stylistInfo.vtl");
        return new ModelAndView(model, layout);
      } else {
        model.put("stylists", Stylist.all());
        model.put("clients", Client.all());
        model.put("template", "templates/index.vtl");
        return new ModelAndView(model, layout);
      }
    }, new VelocityTemplateEngine());

    post("/stylist/:stylist_id/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":stylist_id")));
      stylist.delete();
      model.put("stylists", Stylist.all());
      model.put("clients", Client.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/client/:client_id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.params(":client_id")));
      Stylist stylist = Stylist.find(client.getStylistId());
      model.put("stylist", stylist);
      model.put("client", client);
      model.put("stylists", Stylist.all());
      model.put("template", "templates/clientInfo.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/client/:client_id/update", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.params(":client_id")));
      String name = request.queryParams("updateName");
      String number = request.queryParams("updateNumber");
      client.update(name, number);
      Stylist stylist = Stylist.find(client.getStylistId());
      model.put("stylist", stylist);
      model.put("client", client);
      response.redirect(request.headers("Referer"));
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/client/:client_id/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.params(":client_id")));
      client.delete();
      model.put("stylists", Stylist.all());
      model.put("clients", Client.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/client/:client_id/assign", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.params(":client_id")));
      int stylist_id = Integer.parseInt(request.queryParams("stylist_id"));
      client.assign(client.getId(), stylist_id);
      model.put("stylists", Stylist.all());
      model.put("clients", Client.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
