package tk.puncha.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import tk.puncha.dao.OwnerDAO;
import tk.puncha.dao.PetDAO;
import tk.puncha.models.Owner;

import java.util.List;

@Controller
@RequestMapping("/owners")
public class OwnerController {

  @Autowired
  private OwnerDAO ownerDAO;

  @Autowired
  private PetDAO petDAO;

  @RequestMapping(path = {"", "index"}, method = RequestMethod.GET)
  public ModelAndView index() {
    List<Owner> owners = ownerDAO.getAllOwners();
    ModelAndView modelView = new ModelAndView();
    modelView.addObject("owners", owners);
    modelView.setViewName("owner/index");
    return modelView;
  }

  @RequestMapping(path = {"{ownerId}"}, method = RequestMethod.GET)
  public ModelAndView viewOwner(@PathVariable int ownerId) {
    Owner owner = ownerDAO.getOwnerById(ownerId);
    ModelAndView modelView = new ModelAndView();
    modelView.addObject(owner);
    modelView.setViewName("owner/view");
    return modelView;
  }

  @RequestMapping(path = "new", method = RequestMethod.GET)
  public ModelAndView newOwnerForm() {
    throw new RuntimeException("Not implemented.");
  }

  @RequestMapping(path = "new", method = RequestMethod.POST)
  public ModelAndView createNewOwner() {
    // dispatch to ownerDAO to create a new owner
    throw new RuntimeException("Not implemented.");
  }

  @RequestMapping(path = "delete", method = RequestMethod.POST)
  public String deleteOwner(Owner owner) {
    int ownerId = owner.getId();
    petDAO.deletePetsByOwnerId(ownerId);
    ownerDAO.deleteOwner(ownerId);
    return "redirect:/owners";
  }
}
