/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

/**
 *
 * @author 33652
 */
public class CtrlPrincipal {
    
    private CtrlLogin ctrlLogin = null;
    private CtrlRegister ctrlRegister = null;
    private CtrlStart ctrlStart = null;
    
    
    public void action() {
        if (ctrlStart == null) {
            ctrlStart = new CtrlStart(this);

        }
        ctrlStart.getVue().setEnabled(true);
        ctrlStart.getVue().setVisible(true);
    }
    
    
    public void action(EnumAction action) {
        switch (action) {
            case INSCRIPTION_PAGE: // activation de vuePresence depuis vueMenu
                menuInscription();
                break;
            case CONNEXION: // activation de vuePresence depuis vueMenu
                menuConnexion();
                break;
          
        }

    }
    
      private void menuInscription() {
        if (ctrlRegister == null) {
            ctrlRegister = new CtrlRegister(this);
        }

        ctrlRegister.getVue().setEnabled(true);
        ctrlRegister.getVue().setVisible(true);
    }
      
       private void menuConnexion() {
        if (ctrlLogin == null) {
            ctrlLogin = new CtrlLogin(this);
        }

        ctrlLogin.getVue().setEnabled(true);
        ctrlLogin.getVue().setVisible(true);
    }
      
}
