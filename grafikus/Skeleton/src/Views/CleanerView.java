package Views;

import Models.Character;

import javax.swing.*;
import java.util.Locale;

public class CleanerView extends CharacterView{

    /**
     * CleanerView osztály konstruktora, paramétereit továbbadja a CharacterView konstruktorának
     * @param Image A kép neve, amivel majd megjelenik a karakter
     * @param character Az a Character, akihez tartozik a kinézet
     */
    public CleanerView(String Image, Character character){
        super(Image, character);
    }

}
