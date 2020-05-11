package primitives;

/**
 * Examines the factors of light reflected from the geometries.
 */
public class Material {
    double _kD;
    double _kS;
    int _nShininess;

    /**
     * @param _kD          the  _kD
     * @param _kS          the  _kS
     * @param _nShininess  the  _nShininess
     */
    public Material(double _kD, double _kS, int _nShininess) {
        this._kD = _kD;
        this._kS = _kS;
        this._nShininess = _nShininess;
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
     * @return the _kS
     */
    public double getKs() {
        return _kS;
    }
    /**
     * @return the _nShininess
     */
    public int getnShininess() {
        return _nShininess;
    }
}
