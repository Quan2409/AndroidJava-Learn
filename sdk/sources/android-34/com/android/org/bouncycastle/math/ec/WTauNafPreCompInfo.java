/* GENERATED SOURCE. DO NOT MODIFY. */
package com.android.org.bouncycastle.math.ec;

/**
 * Class holding precomputation data for the WTNAF (Window
 * <code>&tau;</code>-adic Non-Adjacent Form) algorithm.
 * @hide This class is not part of the Android public SDK API
 */
public class WTauNafPreCompInfo implements PreCompInfo
{
    /**
     * Array holding the precomputed <code>ECPoint.AbstractF2m</code>s used for the
     * WTNAF multiplication.
     */
    protected ECPoint.AbstractF2m[] preComp = null;

    public ECPoint.AbstractF2m[] getPreComp()
    {
        return preComp;
    }

    public void setPreComp(ECPoint.AbstractF2m[] preComp)
    {
        this.preComp = preComp;
    }
}
