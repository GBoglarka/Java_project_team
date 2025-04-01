package Views;

import Models.Character;

import javax.swing.*;

public class StudentView extends CharacterView{

    /**
     * StudentView osztály konstruktora, paramétereit továbbadja a CharacterView konstruktorának
     * @param Image A kép neve, amivel majd megjelenik a karakter
     * @param character Az a Character, akihez tartozik a kinézet
     */
    public StudentView(String Image, Character character){
        super(Image, character);
    }

}
