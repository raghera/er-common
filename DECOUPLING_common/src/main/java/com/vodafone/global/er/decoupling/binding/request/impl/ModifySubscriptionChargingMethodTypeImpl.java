//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.11.07 at 04:44:10 PM GMT 
//


package com.vodafone.global.er.decoupling.binding.request.impl;

public class ModifySubscriptionChargingMethodTypeImpl implements com.vodafone.global.er.decoupling.binding.request.ModifySubscriptionChargingMethodType, com.sun.xml.bind.JAXBObject, com.vodafone.global.er.decoupling.binding.request.impl.runtime.UnmarshallableObject, com.vodafone.global.er.decoupling.binding.request.impl.runtime.XMLSerializable, com.vodafone.global.er.decoupling.binding.request.impl.runtime.ValidatableObject
{

    protected boolean has_ApplyPenaltyCharge;
    protected boolean _ApplyPenaltyCharge;
    protected boolean has_ChargingMethod;
    protected int _ChargingMethod;
    protected java.lang.String _CsrId;
    protected java.lang.String _SubscriptionId;
    protected boolean has_InactivateGraceOrSuspendedSub;
    protected boolean _InactivateGraceOrSuspendedSub;
    protected boolean has_DeviceType;
    protected int _DeviceType;
    protected java.lang.String _Msisdn;
    protected java.lang.String _LogId;
    protected java.lang.String _Reason;
    public final static java.lang.Class version = (com.vodafone.global.er.decoupling.binding.request.impl.JAXBVersion.class);
    private static com.sun.msv.grammar.Grammar schemaFragment;

    private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
        return (com.vodafone.global.er.decoupling.binding.request.ModifySubscriptionChargingMethodType.class);
    }

    public boolean isApplyPenaltyCharge() {
        return _ApplyPenaltyCharge;
    }

    public void setApplyPenaltyCharge(boolean value) {
        _ApplyPenaltyCharge = value;
        has_ApplyPenaltyCharge = true;
    }

    public int getChargingMethod() {
        return _ChargingMethod;
    }

    public void setChargingMethod(int value) {
        _ChargingMethod = value;
        has_ChargingMethod = true;
    }

    public java.lang.String getCsrId() {
        return _CsrId;
    }

    public void setCsrId(java.lang.String value) {
        _CsrId = value;
    }

    public java.lang.String getSubscriptionId() {
        return _SubscriptionId;
    }

    public void setSubscriptionId(java.lang.String value) {
        _SubscriptionId = value;
    }

    public boolean isInactivateGraceOrSuspendedSub() {
        return _InactivateGraceOrSuspendedSub;
    }

    public void setInactivateGraceOrSuspendedSub(boolean value) {
        _InactivateGraceOrSuspendedSub = value;
        has_InactivateGraceOrSuspendedSub = true;
    }

    public int getDeviceType() {
        return _DeviceType;
    }

    public void setDeviceType(int value) {
        _DeviceType = value;
        has_DeviceType = true;
    }

    public java.lang.String getMsisdn() {
        return _Msisdn;
    }

    public void setMsisdn(java.lang.String value) {
        _Msisdn = value;
    }

    public java.lang.String getLogId() {
        return _LogId;
    }

    public void setLogId(java.lang.String value) {
        _LogId = value;
    }

    public java.lang.String getReason() {
        return _Reason;
    }

    public void setReason(java.lang.String value) {
        _Reason = value;
    }

    public com.vodafone.global.er.decoupling.binding.request.impl.runtime.UnmarshallingEventHandler createUnmarshaller(com.vodafone.global.er.decoupling.binding.request.impl.runtime.UnmarshallingContext context) {
        return new com.vodafone.global.er.decoupling.binding.request.impl.ModifySubscriptionChargingMethodTypeImpl.Unmarshaller(context);
    }

    public void serializeBody(com.vodafone.global.er.decoupling.binding.request.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        if (!has_ChargingMethod) {
            context.reportError(com.sun.xml.bind.serializer.Util.createMissingObjectError(this, "ChargingMethod"));
        }
        context.startElement("", "msisdn");
        context.endNamespaceDecls();
        context.endAttributes();
        try {
            context.text(((java.lang.String) _Msisdn), "Msisdn");
        } catch (java.lang.Exception e) {
            com.vodafone.global.er.decoupling.binding.request.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endElement();
        context.startElement("", "subscription-id");
        context.endNamespaceDecls();
        context.endAttributes();
        try {
            context.text(((java.lang.String) _SubscriptionId), "SubscriptionId");
        } catch (java.lang.Exception e) {
            com.vodafone.global.er.decoupling.binding.request.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endElement();
        context.startElement("", "charging-method");
        context.endNamespaceDecls();
        context.endAttributes();
        try {
            context.text(javax.xml.bind.DatatypeConverter.printInt(((int) _ChargingMethod)), "ChargingMethod");
        } catch (java.lang.Exception e) {
            com.vodafone.global.er.decoupling.binding.request.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endElement();
        if (has_DeviceType) {
            context.startElement("", "device-type");
            context.endNamespaceDecls();
            context.endAttributes();
            try {
                context.text(javax.xml.bind.DatatypeConverter.printInt(((int) _DeviceType)), "DeviceType");
            } catch (java.lang.Exception e) {
                com.vodafone.global.er.decoupling.binding.request.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endElement();
        }
        if (_CsrId!= null) {
            context.startElement("", "csr-id");
            context.endNamespaceDecls();
            context.endAttributes();
            try {
                context.text(((java.lang.String) _CsrId), "CsrId");
            } catch (java.lang.Exception e) {
                com.vodafone.global.er.decoupling.binding.request.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endElement();
        }
        if (_Reason!= null) {
            context.startElement("", "reason");
            context.endNamespaceDecls();
            context.endAttributes();
            try {
                context.text(((java.lang.String) _Reason), "Reason");
            } catch (java.lang.Exception e) {
                com.vodafone.global.er.decoupling.binding.request.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endElement();
        }
        if (_LogId!= null) {
            context.startElement("", "log-id");
            context.endNamespaceDecls();
            context.endAttributes();
            try {
                context.text(((java.lang.String) _LogId), "LogId");
            } catch (java.lang.Exception e) {
                com.vodafone.global.er.decoupling.binding.request.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endElement();
        }
        if (has_ApplyPenaltyCharge) {
            context.startElement("", "apply-penalty-charge");
            context.endNamespaceDecls();
            context.endAttributes();
            try {
                context.text(javax.xml.bind.DatatypeConverter.printBoolean(((boolean) _ApplyPenaltyCharge)), "ApplyPenaltyCharge");
            } catch (java.lang.Exception e) {
                com.vodafone.global.er.decoupling.binding.request.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endElement();
        }
        if (has_InactivateGraceOrSuspendedSub) {
            context.startElement("", "inactivate-grace-or-suspended-sub");
            context.endNamespaceDecls();
            context.endAttributes();
            try {
                context.text(javax.xml.bind.DatatypeConverter.printBoolean(((boolean) _InactivateGraceOrSuspendedSub)), "InactivateGraceOrSuspendedSub");
            } catch (java.lang.Exception e) {
                com.vodafone.global.er.decoupling.binding.request.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endElement();
        }
    }

    public void serializeAttributes(com.vodafone.global.er.decoupling.binding.request.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        if (!has_ChargingMethod) {
            context.reportError(com.sun.xml.bind.serializer.Util.createMissingObjectError(this, "ChargingMethod"));
        }
    }

    public void serializeURIs(com.vodafone.global.er.decoupling.binding.request.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        if (!has_ChargingMethod) {
            context.reportError(com.sun.xml.bind.serializer.Util.createMissingObjectError(this, "ChargingMethod"));
        }
    }

    public java.lang.Class getPrimaryInterface() {
        return (com.vodafone.global.er.decoupling.binding.request.ModifySubscriptionChargingMethodType.class);
    }

    public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
        if (schemaFragment == null) {
            schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize((
 "\u00ac\u00ed\u0000\u0005sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.su"
+"n.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/gra"
+"mmar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expressi"
+"on\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0002L\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava/lang/Boolean;L\u0000\u000b"
+"expandedExpq\u0000~\u0000\u0002xpppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000pp"
+"sq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsr\u0000\'com.sun.msv.grammar.trex.ElementPattern\u0000"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\tnameClasst\u0000\u001fLcom/sun/msv/grammar/NameClass;xr\u0000\u001e"
+"com.sun.msv.grammar.ElementExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002Z\u0000\u001aignoreUndeclared"
+"AttributesL\u0000\fcontentModelq\u0000~\u0000\u0002xq\u0000~\u0000\u0003pp\u0000sq\u0000~\u0000\u0000ppsr\u0000\u001bcom.sun.m"
+"sv.grammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000\u001fLorg/relaxng/datatype/D"
+"atatype;L\u0000\u0006exceptq\u0000~\u0000\u0002L\u0000\u0004namet\u0000\u001dLcom/sun/msv/util/StringPair"
+";xq\u0000~\u0000\u0003sr\u0000\u0011java.lang.Boolean\u00cd r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000psr\u0000#com.s"
+"un.msv.datatype.xsd.StringType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001Z\u0000\risAlwaysValidxr\u0000"
+"*com.sun.msv.datatype.xsd.BuiltinAtomicType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%co"
+"m.sun.msv.datatype.xsd.ConcreteType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\'com.sun.ms"
+"v.datatype.xsd.XSDatatypeImpl\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\fnamespaceUrit\u0000\u0012Lj"
+"ava/lang/String;L\u0000\btypeNameq\u0000~\u0000\u001cL\u0000\nwhiteSpacet\u0000.Lcom/sun/msv"
+"/datatype/xsd/WhiteSpaceProcessor;xpt\u0000 http://www.w3.org/200"
+"1/XMLSchemat\u0000\u0006stringsr\u00005com.sun.msv.datatype.xsd.WhiteSpaceP"
+"rocessor$Preserve\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000,com.sun.msv.datatype.xsd.Whi"
+"teSpaceProcessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xp\u0001sr\u00000com.sun.msv.grammar.Expres"
+"sion$NullSetExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003q\u0000~\u0000\u0017psr\u0000\u001bcom.sun.msv"
+".util.StringPair\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u001cL\u0000\fnamespaceURIq"
+"\u0000~\u0000\u001cxpq\u0000~\u0000 q\u0000~\u0000\u001fsr\u0000\u001dcom.sun.msv.grammar.ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000"
+"xq\u0000~\u0000\u0001ppsr\u0000 com.sun.msv.grammar.AttributeExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003ex"
+"pq\u0000~\u0000\u0002L\u0000\tnameClassq\u0000~\u0000\u000exq\u0000~\u0000\u0003q\u0000~\u0000\u0017psq\u0000~\u0000\u0012ppsr\u0000\"com.sun.msv.d"
+"atatype.xsd.QnameType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0019q\u0000~\u0000\u001ft\u0000\u0005QNamesr\u00005com.s"
+"un.msv.datatype.xsd.WhiteSpaceProcessor$Collapse\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000x"
+"q\u0000~\u0000\"q\u0000~\u0000%sq\u0000~\u0000&q\u0000~\u0000/q\u0000~\u0000\u001fsr\u0000#com.sun.msv.grammar.SimpleName"
+"Class\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u001cL\u0000\fnamespaceURIq\u0000~\u0000\u001cxr\u0000\u001dcom"
+".sun.msv.grammar.NameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpt\u0000\u0004typet\u0000)http://www."
+"w3.org/2001/XMLSchema-instancesr\u00000com.sun.msv.grammar.Expres"
+"sion$EpsilonExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003sq\u0000~\u0000\u0016\u0001psq\u0000~\u00003t\u0000\u0006msis"
+"dnt\u0000\u0000sq\u0000~\u0000\rpp\u0000sq\u0000~\u0000\u0000ppq\u0000~\u0000\u0015sq\u0000~\u0000(ppsq\u0000~\u0000*q\u0000~\u0000\u0017pq\u0000~\u0000,q\u0000~\u00005q\u0000~"
+"\u00009sq\u0000~\u00003t\u0000\u000fsubscription-idq\u0000~\u0000=sq\u0000~\u0000\rpp\u0000sq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0012ppsr\u0000 "
+"com.sun.msv.datatype.xsd.IntType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000+com.sun.msv.d"
+"atatype.xsd.IntegerDerivedType\u0099\u00f1]\u0090&6k\u00be\u0002\u0000\u0001L\u0000\nbaseFacetst\u0000)Lco"
+"m/sun/msv/datatype/xsd/XSDatatypeImpl;xq\u0000~\u0000\u0019q\u0000~\u0000\u001ft\u0000\u0003intq\u0000~\u00001"
+"sr\u0000*com.sun.msv.datatype.xsd.MaxInclusiveFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000"
+"#com.sun.msv.datatype.xsd.RangeFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\nlimitValue"
+"t\u0000\u0012Ljava/lang/Object;xr\u00009com.sun.msv.datatype.xsd.DataTypeWi"
+"thValueConstraintFacet\"\u00a7Ro\u00ca\u00c7\u008aT\u0002\u0000\u0000xr\u0000*com.sun.msv.datatype.xs"
+"d.DataTypeWithFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0005Z\u0000\fisFacetFixedZ\u0000\u0012needValueChe"
+"ckFlagL\u0000\bbaseTypeq\u0000~\u0000IL\u0000\fconcreteTypet\u0000\'Lcom/sun/msv/datatyp"
+"e/xsd/ConcreteType;L\u0000\tfacetNameq\u0000~\u0000\u001cxq\u0000~\u0000\u001bppq\u0000~\u00001\u0000\u0001sr\u0000*com.s"
+"un.msv.datatype.xsd.MinInclusiveFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000Mppq\u0000~\u0000"
+"1\u0000\u0000sr\u0000!com.sun.msv.datatype.xsd.LongType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000Hq\u0000~"
+"\u0000\u001ft\u0000\u0004longq\u0000~\u00001sq\u0000~\u0000Lppq\u0000~\u00001\u0000\u0001sq\u0000~\u0000Sppq\u0000~\u00001\u0000\u0000sr\u0000$com.sun.msv."
+"datatype.xsd.IntegerType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000Hq\u0000~\u0000\u001ft\u0000\u0007integerq\u0000~\u0000"
+"1sr\u0000,com.sun.msv.datatype.xsd.FractionDigitsFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001"
+"I\u0000\u0005scalexr\u0000;com.sun.msv.datatype.xsd.DataTypeWithLexicalCons"
+"traintFacetT\u0090\u001c>\u001azb\u00ea\u0002\u0000\u0000xq\u0000~\u0000Pppq\u0000~\u00001\u0001\u0000sr\u0000#com.sun.msv.datatyp"
+"e.xsd.NumberType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0019q\u0000~\u0000\u001ft\u0000\u0007decimalq\u0000~\u00001q\u0000~\u0000at\u0000"
+"\u000efractionDigits\u0000\u0000\u0000\u0000q\u0000~\u0000[t\u0000\fminInclusivesr\u0000\u000ejava.lang.Long;\u008b\u00e4"
+"\u0090\u00cc\u008f#\u00df\u0002\u0000\u0001J\u0000\u0005valuexr\u0000\u0010java.lang.Number\u0086\u00ac\u0095\u001d\u000b\u0094\u00e0\u008b\u0002\u0000\u0000xp\u0080\u0000\u0000\u0000\u0000\u0000\u0000\u0000q\u0000~"
+"\u0000[t\u0000\fmaxInclusivesq\u0000~\u0000e\u007f\u00ff\u00ff\u00ff\u00ff\u00ff\u00ff\u00ffq\u0000~\u0000Vq\u0000~\u0000dsr\u0000\u0011java.lang.Integ"
+"er\u0012\u00e2\u00a0\u00a4\u00f7\u0081\u00878\u0002\u0000\u0001I\u0000\u0005valuexq\u0000~\u0000f\u0080\u0000\u0000\u0000q\u0000~\u0000Vq\u0000~\u0000hsq\u0000~\u0000j\u007f\u00ff\u00ff\u00ffq\u0000~\u0000%sq\u0000~"
+"\u0000&q\u0000~\u0000Kq\u0000~\u0000\u001fsq\u0000~\u0000(ppsq\u0000~\u0000*q\u0000~\u0000\u0017pq\u0000~\u0000,q\u0000~\u00005q\u0000~\u00009sq\u0000~\u00003t\u0000\u000fchar"
+"ging-methodq\u0000~\u0000=sq\u0000~\u0000(ppsq\u0000~\u0000\rq\u0000~\u0000\u0017p\u0000sq\u0000~\u0000\u0000ppq\u0000~\u0000Fsq\u0000~\u0000(ppsq"
+"\u0000~\u0000*q\u0000~\u0000\u0017pq\u0000~\u0000,q\u0000~\u00005q\u0000~\u00009sq\u0000~\u00003t\u0000\u000bdevice-typeq\u0000~\u0000=q\u0000~\u00009sq\u0000~\u0000"
+"(ppsq\u0000~\u0000\rq\u0000~\u0000\u0017p\u0000sq\u0000~\u0000\u0000ppq\u0000~\u0000\u0015sq\u0000~\u0000(ppsq\u0000~\u0000*q\u0000~\u0000\u0017pq\u0000~\u0000,q\u0000~\u00005q"
+"\u0000~\u00009sq\u0000~\u00003t\u0000\u0006csr-idq\u0000~\u0000=q\u0000~\u00009sq\u0000~\u0000(ppsq\u0000~\u0000\rq\u0000~\u0000\u0017p\u0000sq\u0000~\u0000\u0000ppq\u0000"
+"~\u0000\u0015sq\u0000~\u0000(ppsq\u0000~\u0000*q\u0000~\u0000\u0017pq\u0000~\u0000,q\u0000~\u00005q\u0000~\u00009sq\u0000~\u00003t\u0000\u0006reasonq\u0000~\u0000=q\u0000"
+"~\u00009sq\u0000~\u0000(ppsq\u0000~\u0000\rq\u0000~\u0000\u0017p\u0000sq\u0000~\u0000\u0000ppq\u0000~\u0000\u0015sq\u0000~\u0000(ppsq\u0000~\u0000*q\u0000~\u0000\u0017pq\u0000~"
+"\u0000,q\u0000~\u00005q\u0000~\u00009sq\u0000~\u00003t\u0000\u0006log-idq\u0000~\u0000=q\u0000~\u00009sq\u0000~\u0000(ppsq\u0000~\u0000\rq\u0000~\u0000\u0017p\u0000sq"
+"\u0000~\u0000\u0000ppsq\u0000~\u0000\u0012ppsr\u0000$com.sun.msv.datatype.xsd.BooleanType\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0019q\u0000~\u0000\u001ft\u0000\u0007booleanq\u0000~\u00001q\u0000~\u0000%sq\u0000~\u0000&q\u0000~\u0000\u0094q\u0000~\u0000\u001fsq\u0000~\u0000(pp"
+"sq\u0000~\u0000*q\u0000~\u0000\u0017pq\u0000~\u0000,q\u0000~\u00005q\u0000~\u00009sq\u0000~\u00003t\u0000\u0014apply-penalty-chargeq\u0000~\u0000"
+"=q\u0000~\u00009sq\u0000~\u0000(ppsq\u0000~\u0000\rq\u0000~\u0000\u0017p\u0000sq\u0000~\u0000\u0000ppq\u0000~\u0000\u0091sq\u0000~\u0000(ppsq\u0000~\u0000*q\u0000~\u0000\u0017p"
+"q\u0000~\u0000,q\u0000~\u00005q\u0000~\u00009sq\u0000~\u00003t\u0000!inactivate-grace-or-suspended-subq\u0000~"
+"\u0000=q\u0000~\u00009sr\u0000\"com.sun.msv.grammar.ExpressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\be"
+"xpTablet\u0000/Lcom/sun/msv/grammar/ExpressionPool$ClosedHash;xps"
+"r\u0000-com.sun.msv.grammar.ExpressionPool$ClosedHash\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0003\u0000\u0003I"
+"\u0000\u0005countB\u0000\rstreamVersionL\u0000\u0006parentt\u0000$Lcom/sun/msv/grammar/Expr"
+"essionPool;xp\u0000\u0000\u0000 \u0001pq\u0000~\u0000\tq\u0000~\u0000yq\u0000~\u0000\u0011q\u0000~\u0000?q\u0000~\u0000Eq\u0000~\u0000tq\u0000~\u0000{q\u0000~\u0000\u0082q"
+"\u0000~\u0000\u0080q\u0000~\u0000\u0089q\u0000~\u0000\u0087q\u0000~\u0000\u0006q\u0000~\u0000\u0090q\u0000~\u0000\u009cq\u0000~\u0000\nq\u0000~\u0000\u0005q\u0000~\u0000)q\u0000~\u0000@q\u0000~\u0000nq\u0000~\u0000\fq"
+"\u0000~\u0000uq\u0000~\u0000rq\u0000~\u0000|q\u0000~\u0000\u0083q\u0000~\u0000\u008aq\u0000~\u0000\u0096q\u0000~\u0000\u009dq\u0000~\u0000\u000bq\u0000~\u0000\bq\u0000~\u0000\u008eq\u0000~\u0000\u009aq\u0000~\u0000\u0007x"));
        }
        return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
    }

    public class Unmarshaller
        extends com.vodafone.global.er.decoupling.binding.request.impl.runtime.AbstractUnmarshallingEventHandlerImpl
    {


        public Unmarshaller(com.vodafone.global.er.decoupling.binding.request.impl.runtime.UnmarshallingContext context) {
            super(context, "----------------------------");
        }

        protected Unmarshaller(com.vodafone.global.er.decoupling.binding.request.impl.runtime.UnmarshallingContext context, int startState) {
            this(context);
            state = startState;
        }

        public java.lang.Object owner() {
            return com.vodafone.global.er.decoupling.binding.request.impl.ModifySubscriptionChargingMethodTypeImpl.this;
        }

        public void enterElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname, org.xml.sax.Attributes __atts)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  0 :
                        if (("msisdn" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 1;
                            return ;
                        }
                        break;
                    case  6 :
                        if (("charging-method" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 7;
                            return ;
                        }
                        break;
                    case  9 :
                        if (("device-type" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 10;
                            return ;
                        }
                        state = 12;
                        continue outer;
                    case  18 :
                        if (("log-id" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 19;
                            return ;
                        }
                        state = 21;
                        continue outer;
                    case  12 :
                        if (("csr-id" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 13;
                            return ;
                        }
                        state = 15;
                        continue outer;
                    case  3 :
                        if (("subscription-id" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 4;
                            return ;
                        }
                        break;
                    case  15 :
                        if (("reason" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 16;
                            return ;
                        }
                        state = 18;
                        continue outer;
                    case  27 :
                        revertToParentFromEnterElement(___uri, ___local, ___qname, __atts);
                        return ;
                    case  21 :
                        if (("apply-penalty-charge" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 22;
                            return ;
                        }
                        state = 24;
                        continue outer;
                    case  24 :
                        if (("inactivate-grace-or-suspended-sub" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 25;
                            return ;
                        }
                        state = 27;
                        continue outer;
                }
                super.enterElement(___uri, ___local, ___qname, __atts);
                break;
            }
        }

        public void leaveElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  17 :
                        if (("reason" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 18;
                            return ;
                        }
                        break;
                    case  2 :
                        if (("msisdn" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 3;
                            return ;
                        }
                        break;
                    case  9 :
                        state = 12;
                        continue outer;
                    case  20 :
                        if (("log-id" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 21;
                            return ;
                        }
                        break;
                    case  26 :
                        if (("inactivate-grace-or-suspended-sub" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 27;
                            return ;
                        }
                        break;
                    case  5 :
                        if (("subscription-id" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 6;
                            return ;
                        }
                        break;
                    case  18 :
                        state = 21;
                        continue outer;
                    case  8 :
                        if (("charging-method" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 9;
                            return ;
                        }
                        break;
                    case  23 :
                        if (("apply-penalty-charge" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 24;
                            return ;
                        }
                        break;
                    case  14 :
                        if (("csr-id" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 15;
                            return ;
                        }
                        break;
                    case  12 :
                        state = 15;
                        continue outer;
                    case  15 :
                        state = 18;
                        continue outer;
                    case  27 :
                        revertToParentFromLeaveElement(___uri, ___local, ___qname);
                        return ;
                    case  21 :
                        state = 24;
                        continue outer;
                    case  24 :
                        state = 27;
                        continue outer;
                    case  11 :
                        if (("device-type" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 12;
                            return ;
                        }
                        break;
                }
                super.leaveElement(___uri, ___local, ___qname);
                break;
            }
        }

        public void enterAttribute(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  9 :
                        state = 12;
                        continue outer;
                    case  18 :
                        state = 21;
                        continue outer;
                    case  12 :
                        state = 15;
                        continue outer;
                    case  15 :
                        state = 18;
                        continue outer;
                    case  27 :
                        revertToParentFromEnterAttribute(___uri, ___local, ___qname);
                        return ;
                    case  21 :
                        state = 24;
                        continue outer;
                    case  24 :
                        state = 27;
                        continue outer;
                }
                super.enterAttribute(___uri, ___local, ___qname);
                break;
            }
        }

        public void leaveAttribute(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  9 :
                        state = 12;
                        continue outer;
                    case  18 :
                        state = 21;
                        continue outer;
                    case  12 :
                        state = 15;
                        continue outer;
                    case  15 :
                        state = 18;
                        continue outer;
                    case  27 :
                        revertToParentFromLeaveAttribute(___uri, ___local, ___qname);
                        return ;
                    case  21 :
                        state = 24;
                        continue outer;
                    case  24 :
                        state = 27;
                        continue outer;
                }
                super.leaveAttribute(___uri, ___local, ___qname);
                break;
            }
        }

        public void handleText(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                try {
                    switch (state) {
                        case  13 :
                            state = 14;
                            eatText1(value);
                            return ;
                        case  9 :
                            state = 12;
                            continue outer;
                        case  7 :
                            state = 8;
                            eatText2(value);
                            return ;
                        case  16 :
                            state = 17;
                            eatText3(value);
                            return ;
                        case  25 :
                            state = 26;
                            eatText4(value);
                            return ;
                        case  18 :
                            state = 21;
                            continue outer;
                        case  4 :
                            state = 5;
                            eatText5(value);
                            return ;
                        case  22 :
                            state = 23;
                            eatText6(value);
                            return ;
                        case  10 :
                            state = 11;
                            eatText7(value);
                            return ;
                        case  12 :
                            state = 15;
                            continue outer;
                        case  15 :
                            state = 18;
                            continue outer;
                        case  27 :
                            revertToParentFromText(value);
                            return ;
                        case  21 :
                            state = 24;
                            continue outer;
                        case  1 :
                            state = 2;
                            eatText8(value);
                            return ;
                        case  19 :
                            state = 20;
                            eatText9(value);
                            return ;
                        case  24 :
                            state = 27;
                            continue outer;
                    }
                } catch (java.lang.RuntimeException e) {
                    handleUnexpectedTextException(value, e);
                }
                break;
            }
        }

        private void eatText1(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _CsrId = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText2(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _ChargingMethod = javax.xml.bind.DatatypeConverter.parseInt(com.sun.xml.bind.WhiteSpaceProcessor.collapse(value));
                has_ChargingMethod = true;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText3(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _Reason = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText4(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _InactivateGraceOrSuspendedSub = javax.xml.bind.DatatypeConverter.parseBoolean(com.sun.xml.bind.WhiteSpaceProcessor.collapse(value));
                has_InactivateGraceOrSuspendedSub = true;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText5(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _SubscriptionId = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText6(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _ApplyPenaltyCharge = javax.xml.bind.DatatypeConverter.parseBoolean(com.sun.xml.bind.WhiteSpaceProcessor.collapse(value));
                has_ApplyPenaltyCharge = true;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText7(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _DeviceType = javax.xml.bind.DatatypeConverter.parseInt(com.sun.xml.bind.WhiteSpaceProcessor.collapse(value));
                has_DeviceType = true;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText8(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _Msisdn = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText9(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _LogId = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

    }

}