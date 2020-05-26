package primitives;

/**
 * Examines the factors of light reflected from the geometries.
 */
public class Material {
    private double _kD;
    private double _kS;
    private double _kT;
    private double _kR;
    private int _nShininess;

    /**
     * Instantiates a new Material.
     *
     * @param kD         the k d
     * @param kS         the k s
     * @param nShininess the n shininess
     * @param kT         the k t
     * @param kR         the k r
     */
    public Material(double kD, double kS, int nShininess, double kT, double kR) {
        _kD = kD;
        _kS = kS;
        _nShininess = nShininess;
        _kR = kR;
        _kT = kT;
    }

    /**
     * Instantiates a new Material.
     *
     * @param kD         the k d
     * @param kS         the k s
     * @param nShininess the n shininess
     */
    public Material(double kD, double kS, int nShininess) {
        this(kD, kS, nShininess, 0, 0);
    }

    /**
     * getters Material
     *
     * @return the _kD
     */
    public double getKd() {
        return _kD;
    }

    /**
     * Gets ks.
     *
     * @return the _kS
     */
    public double getKs() {
        return _kS;
    }

    /**
     * Get kt double.
     *
     * @return the double
     */
    public double getKt() {
        return _kT;
    }

    /**
     * Get kr double.
     *
     * @return the double
     */
    public double getKr() {
        return _kR;
    }

    /**
     * Gets shininess.
     *
     * @return the _nShininess
     */
    public int getnShininess() {
        return _nShininess;
    }
}
