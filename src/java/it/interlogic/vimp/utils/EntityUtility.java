package it.interlogic.vimp.utils;

import it.interlogic.vimp.data.jpa.model.TranslatableCodifica;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityUtility
{
    private EntityUtility(){}

    public static final boolean TO_INSERT= true;
    public static final boolean TO_DELETE= false;

    public static boolean equals(BigDecimal x, BigDecimal y)
    {
        return x!=null && y!=null && x.equals(y);
    }

    public static boolean isNotValid(BigDecimal x)
    {
        return x == null || x.longValue()<=0;
    }

    public static boolean isValid(BigDecimal x)
    {
        return !isNotValid(x);
    }

    public static boolean isNotValid(TranslatableCodifica t){
        return t == null || isNotValid(t.getId());
    }

    public static boolean isNotValid(List<?> list){
        return list == null || list.isEmpty();
    }

    public static <E> int hashCode(E... arg)
    {
        return Arrays.hashCode(arg);
    }

    public static Map<BigDecimal,Boolean> mergeValues(List<?> orginalValues, List<?> formValues){
        List<BigDecimal> orig = toBigdec(orginalValues);
        List<BigInteger> form = toBigint(formValues);
        Map<BigDecimal,Boolean> result = new HashMap<BigDecimal, Boolean>();
        for(BigDecimal o : orig)
        {
            result.put(o,TO_DELETE);
        }
        for(BigInteger n : form)
        {
            BigDecimal val = new BigDecimal(n);
            if(result.containsKey(val) && !result.get(val)){
                result.remove(val);
            } else {
                result.put(val, TO_INSERT);
            }
        }
        return result;
    }

    public static List<BigInteger> toBigint(List<?> list){
        List<BigInteger> result = new ArrayList<BigInteger>();
        for(Object d : list)
        {
            result.add(toBiginteger(d));
        }
        return result;
    }

    public static List<BigDecimal> toBigdec(List<?> list)
    {
        List<BigDecimal> result = new ArrayList<BigDecimal>();
        for(Object i : list)
        {
            result.add(toBigDecimal(i));
        }
        return result;
    }

    public static BigInteger toBiginteger(Object d)
    {
        if(d instanceof BigDecimal)
        {
            return ((BigDecimal) d).toBigInteger();
        } else if(d instanceof BigInteger) {
            return (BigInteger) d;
        } else {
            throw new UnsupportedOperationException("Cannot convert " + d.getClass() + " to BigInteger");
        }
    }

    public static BigDecimal toBigDecimal(Object i)
    {
        if(i instanceof BigDecimal)
        {
            return (BigDecimal) i;
        } else if(i instanceof BigInteger) {
            return new BigDecimal((BigInteger)i);
        } else {
            throw new UnsupportedOperationException("Cannot convert " + i.getClass() + " to BigDecimal");
        }
    }

}
