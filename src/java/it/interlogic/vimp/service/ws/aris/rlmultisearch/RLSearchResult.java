package it.interlogic.vimp.service.ws.aris.rlmultisearch;

import java.util.Arrays;

@SuppressWarnings("serial")
public class RLSearchResult  implements java.io.Serializable {
    private ResultElement[] resultRapprLegali;

    public RLSearchResult() {
    }

    public RLSearchResult(
           ResultElement[] resultRapprLegali) {
           this.resultRapprLegali = resultRapprLegali;
    }


    /**
     * Gets the resultRapprLegali value for this RLSearchResult.
     * 
     * @return resultRapprLegali
     */
    public ResultElement[] getResultRapprLegali() {
        return resultRapprLegali;
    }


    /**
     * Sets the resultRapprLegali value for this RLSearchResult.
     * 
     * @param resultRapprLegali
     */
    public void setResultRapprLegali(ResultElement[] resultRapprLegali) {
        this.resultRapprLegali = resultRapprLegali;
    }

    private java.lang.Object __equalsCalc = null;
    @SuppressWarnings("unused")
	public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RLSearchResult)) return false;
        RLSearchResult other = (RLSearchResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultRapprLegali==null && other.getResultRapprLegali()==null) || 
             (this.resultRapprLegali!=null &&
              java.util.Arrays.equals(this.resultRapprLegali, other.getResultRapprLegali())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getResultRapprLegali() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResultRapprLegali());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResultRapprLegali(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RLSearchResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:RLSearch", "RLSearchResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultRapprLegali");
        elemField.setXmlName(new javax.xml.namespace.QName("", "resultRapprLegali"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:RLSearch", "ResultElement"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    @SuppressWarnings("rawtypes")
	public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    @SuppressWarnings("rawtypes")
	public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

	@Override
	public String toString()
	{
		return "RLSearchResult [resultRapprLegali=" + Arrays.toString(resultRapprLegali) + "]";
	}
    
    
    

}
