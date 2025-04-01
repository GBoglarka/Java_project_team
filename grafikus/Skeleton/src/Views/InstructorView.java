package Views;

import Models.Character;

import javax.swing.*;

public class InstructorView extends CharacterView{

    /**
     * InstructorView osztály konstruktora, paramétereit továbbadja a CharacterView konstruktorának
     * @param Image A kép neve, amivel majd megjelenik a karakter
     * @param character Az a Character, akihez tartozik a kinézet
     */
    public InstructorView(String Image, Character character){
        super(Image, character);
    }

}
