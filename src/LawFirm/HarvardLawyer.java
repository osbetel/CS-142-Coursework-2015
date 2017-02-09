package LawFirm;

/**
 * @author ADKN
 * @version 20 Nov 2015, 10:39 PM
 */
public class HarvardLawyer extends Lawyer{
    //20% more pay than lawyer
    //3 days more vacation
    //4x as many vacation forms to fill out "pinkpinkpinkpink"
    @Override
    public int getVacationDays() {
        return super.getVacationDays() + 3;
    }
    @Override
    public double getSalary() {
        return super.getSalary() * 1.20;
    }
    @Override
    public String getVacationForm() {
        String temp = "";
        for (int i = 0; i < 4; i++) {
            temp += super.getVacationForm();
        }
        return temp;
    }
}
