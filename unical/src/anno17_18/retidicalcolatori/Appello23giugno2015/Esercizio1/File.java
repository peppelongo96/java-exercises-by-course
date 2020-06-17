/**
 * 
 */
package anno17_18.retidicalcolatori.Appello23giugno2015.Esercizio1;

import java.io.Serializable;

/**
 * @author Giuseppe Longo
 *
 */
class File implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7652976544618366518L;
	
	private String filename;
	private String content;
	private String[] keywords;
	
	File ( String filename, String content, String[] keywords ) {
		this.filename = filename;
		this.content = content;
		this.keywords = keywords;
	}
	
	String getFilename() {
		return filename;
	}
	
	String[] getKeywords() {
		return keywords;
	}
	
	String stampaKeywords() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		for (int i = 0; i < keywords.length; i++) {
			sb.append(keywords[i]+", ");
		}
		sb.append("]");
		return sb.toString();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Nome: "+filename+
				"\nParole chiave: "+stampaKeywords()+
				"\nContenuto: "+content;
	}

}
