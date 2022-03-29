// Name: Jason Younan
// Description: Gets and returns the cuisine name and information

import java.io.Serializable;

// Implement Serializable to allow the instance to be saved and read from a file
public class Cuisine implements Serializable {
	// The serialVersionUID is used to verify compatibility of senders and
	// receivers. See the document for more details:
	// https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/Serializable.html
	private static final long serialVersionUID = 205L; // SerializableUID
	private String signatureDish;
	private String name;

	// Initialize instance of Cuisine with given parameters
	public Cuisine(String signatureDish, String name) {
		this.name = name;
		this.signatureDish = signatureDish;
	}

	// Get name
	public String getName() {
		return name;
	}

	// Get cuisine String
	@Override
	public String toString() {
		// Return the String containing cuisine Name and signatureDish
		return name + " Cuisine\n" + "Signature Dish:\t" + signatureDish + '\n';
	}
}