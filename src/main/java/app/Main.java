/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import controller.GraduationProgramController;
import java.io.PrintWriter;
import model.GraduationProgram;
import utils.YearValidator;

/**
 *
 * @author Renard Ferreira
 * @author Thiago Albuquerque
 * @author Victor Springer
 */
public class Main {
    
    public static void main(String[] args) throws Exception {
        
        if(validateParams(args)) {
            GraduationProgram graduationProgram = GraduationProgramController.getInstance().validateGraduationProgramName(args[0]);
            
            if(graduationProgram.getName() != null) {
                try(PrintWriter out = new PrintWriter(args[0] + ".txt")) {
                    out.println("Hello World!");
                    out.close();
                }
            }
            else {
                System.out.println("Nome de programa de graduação inválido!");
            }
        }
    }
    
    private static Boolean validateParams(String[] args) {
        
        if(args.length == 3) {
            if(YearValidator.getInstance().validateYear(args[1]) &&
                    YearValidator.getInstance().validateYear(args[2])) {
                
                if(Integer.valueOf(args[1]) > Integer.valueOf(args[2])) {
                    System.out.println("Ano fim deve ser maior ou igual ano início!");
                }
                else {
                    return true;
                }
            }
            else {
                System.out.println("Ano(s) inválido(s)! Os anos devem ser preenchidos no formato 'yyyy'");
            }
        }
        else {
            System.out.println("Número incorreto de parâmetros! "
                    + "Por favor, indique os parâmetros nesta ordem: "
                    + "[nome do programa] [ano início] [ano fim]");
        }
        
        return false;
    }
}
