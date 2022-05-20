package sia.tacocloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import sia.tacocloud.models.Ingredents;
import sia.tacocloud.models.Ingredents.Type;
import sia.tacocloud.models.Taco;
import sia.tacocloud.models.TacoOrder;

import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")


public class DesignTacoController {
    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredents> ingredents = Arrays.asList(
                new Ingredents("FLTO", "Flour Tortilla", Type.SAUCE),
                new Ingredents("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredents("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredents("CARN", "Carnitas", Type.PROTEIN),
                new Ingredents("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredents("LETC", "Lettuce", Type.VEGGIES),
                new Ingredents("CHED", "Cheddar", Type.CHEESE),
                new Ingredents("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredents("SLSA", "Salsa", Type.SAUCE),
                new Ingredents("SRCR", "Sour Cream", Type.SAUCE)
        );

        Type[] types = Ingredents.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredents, type));
        }

    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    private Iterable<Ingredents> filterByType(
            List<Ingredents> ingredents, Type type) {
        return ingredents
                .stream()
                .filter(x->x.getType().equals(type))
                .collect(Collectors.toList());
    }


}
