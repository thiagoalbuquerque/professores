/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import controller.GraduateProgramController;
import controller.OutputController;
import controller.ProfessorController;
import controller.ResumeController;
import java.io.PrintWriter;
import java.util.List;
import model.GraduateProgram;
import model.Professor;
import model.Resume;
import utils.YearValidator;

/**
 *
 * @author Renard Ferreira
 * @author Thiago Albuquerque
 * @author Victor Springer
 */
public class Main {
    /**
     * 
     * @param args Graduate Program name, start year, end year
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        
        if(args != null && validateParams(args)) {
            GraduateProgram graduateProgram = GraduateProgramController.getInstance().validateGraduateProgramName(args[0]);
            Integer startYear = Integer.valueOf(args[1]);
            Integer endYear = Integer.valueOf(args[2]);
            
            if(graduateProgram.getName() != null) {
                List<Professor> professors = ProfessorController.getInstance().getProfessorsList(graduateProgram.getName());
                
                if(professors != null) {
                    List<Resume> resumes = ResumeController.getInstance().getResumesList(professors, startYear, endYear);
                    
                    if(resumes != null && !resumes.isEmpty()) {
                        String output = OutputController.getInstance().writeOutput(resumes);
                        
                        if(output != null) {
                            try(PrintWriter out = new PrintWriter(graduateProgram.getName() + ".txt")) {
                                out.println(output);
                                out.close();
                            }
                        }
                    }
                }
            }
            else {
                System.out.println("Nome de programa de pós-graduação inválido!");
            }
        }
        else if(args == null) {
            System.out.println("Por favor, preencha os parâmetros!");
        }
    }
    
    /**
     * 
     * @param args Main function arguments
     * @return True or false
     */
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
