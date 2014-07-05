package bzh.terrevirtuelle.navisu.domain.nmea.model;

import java.util.StringTokenizer;
import javax.xml.bind.annotation.XmlTransient;

@XmlTransient
public class NMEA {

    protected String device;
    protected String sentence;

    public NMEA() {
    }

    public NMEA(String device) {
        this.device = device;
    }

    public NMEA(String device,
            String sentence) {
        this.device = device;
        this.sentence = sentence;
    }

    /**
     * Get the value of sentence
     *
     * @return the value of sentence
     */
    public String getSentence() {
        return sentence;
    }

    /**
     * Set the value of sentence
     *
     * @param sentence new value of sentence
     */
    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    /**
     *
     * @return
     */
    public String getDevice() {
        return this.device;
    }

    /**
     *
     * @param value
     */
    public void setDevice(String value) {
        this.device = value;
    }

    /**
     *
     * @return
     */
    public String getChecksum() {
        StringTokenizer st = new StringTokenizer(sentence, "*");
        String tmp = st.nextToken();
        tmp = st.nextToken();
        return tmp;
    }

    public boolean getChecksumValidation() {
        String tmp = sentence;
        StringTokenizer st = new StringTokenizer(tmp, "$");
        tmp = st.nextToken();
        st = new StringTokenizer(tmp, "*");
        tmp = st.nextToken();

        int c = 0;
        for (int i = 0; i < tmp.length(); i++) {
            c ^= tmp.charAt(i);
        }
        String result = Integer.toHexString(c).toUpperCase();

        String check = st.nextToken();
        if (result.length() == 1) {
            result = "0" + result;
        }
        return result.equals(check);
    }

    @Override
    public String toString() {
        return "NMEA{" + "device=" + device + ", sentence=" + sentence + '}';
    }

}
