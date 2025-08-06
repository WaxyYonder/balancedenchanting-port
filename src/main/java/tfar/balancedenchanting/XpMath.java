
package tfar.balancedenchanting;

public class XpMath {
    // Vanilla XP to reach exactly the start of level L (0-based).
    public static int xpAtLevel(int level) {
        if (level <= 0) return 0;
        if (level <= 16) {
            // sum_{i=0}^{L-1} (2i + 7) = L^2 + 6L
            return level * level + 6 * level;
        } else if (level <= 31) {
            // sum piecewise: for 17..31 increments are (5i - 38)
            // closed form: 2.5L^2 - 40.5L + 360
            return (int)Math.floor(2.5 * level * level - 40.5 * level + 360);
        } else {
            // 32+: increments are (9i - 158)
            // closed form: 4.5L^2 - 162.5L + 2220
            return (int)Math.floor(4.5 * level * level - 162.5 * level + 2220);
        }
    }

    /** Raw XP required to gain exactly {@code levels} from level 0. */
    public static int rawForLevelsFromZero(int levels) {
        if (levels <= 0) return 0;
        return xpAtLevel(levels) - xpAtLevel(0);
    }
}
