package vue;

import modele.Modele;

public interface Vue {
    public void notifierChangement();
	public void setModele(Modele m);
}
