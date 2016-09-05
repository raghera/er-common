//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.09.03 at 03:16:51 PM BST 
//


package com.vodafone.global.er.decoupling.binding.request.impl;

public class PurchaseTariffAuthorisationTypeImpl implements com.vodafone.global.er.decoupling.binding.request.PurchaseTariffAuthorisationType, com.sun.xml.bind.JAXBObject, com.vodafone.global.er.decoupling.binding.request.impl.runtime.UnmarshallableObject, com.vodafone.global.er.decoupling.binding.request.impl.runtime.XMLSerializable, com.vodafone.global.er.decoupling.binding.request.impl.runtime.ValidatableObject
{

    protected java.lang.String _TransactionId;
    protected java.lang.String _PackageSubscriptionId;
    protected boolean has_IsSuccess;
    protected boolean _IsSuccess;
    protected com.vodafone.global.er.decoupling.binding.request.ReasonCodeType _ReasonCode;
    protected boolean has_Rate;
    protected double _Rate;
    public final static java.lang.Class version = (com.vodafone.global.er.decoupling.binding.request.impl.JAXBVersion.class);
    private static com.sun.msv.grammar.Grammar schemaFragment;

    private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
        return (com.vodafone.global.er.decoupling.binding.request.PurchaseTariffAuthorisationType.class);
    }

    public java.lang.String getTransactionId() {
        return _TransactionId;
    }

    public void setTransactionId(java.lang.String value) {
        _TransactionId = value;
    }

    public java.lang.String getPackageSubscriptionId() {
        return _PackageSubscriptionId;
    }

    public void setPackageSubscriptionId(java.lang.String value) {
        _PackageSubscriptionId = value;
    }

    public boolean isIsSuccess() {
        return _IsSuccess;
    }

    public void setIsSuccess(boolean value) {
        _IsSuccess = value;
        has_IsSuccess = true;
    }

    public com.vodafone.global.er.decoupling.binding.request.ReasonCodeType getReasonCode() {
        return _ReasonCode;
    }

    public void setReasonCode(com.vodafone.global.er.decoupling.binding.request.ReasonCodeType value) {
        _ReasonCode = value;
    }

    public double getRate() {
        return _Rate;
    }

    public void setRate(double value) {
        _Rate = value;
        has_Rate = true;
    }

    public com.vodafone.global.er.decoupling.binding.request.impl.runtime.UnmarshallingEventHandler createUnmarshaller(com.vodafone.global.er.decoupling.binding.request.impl.runtime.UnmarshallingContext context) {
        return new com.vodafone.global.er.decoupling.binding.request.impl.PurchaseTariffAuthorisationTypeImpl.Unmarshaller(context);
    }

    public void serializeBody(com.vodafone.global.er.decoupling.binding.request.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        if (!has_IsSuccess) {
            context.reportError(com.sun.xml.bind.serializer.Util.createMissingObjectError(this, "IsSuccess"));
        }
        if (!has_Rate) {
            context.reportError(com.sun.xml.bind.serializer.Util.createMissingObjectError(this, "Rate"));
        }
        context.startElement("", "is-success");
        context.endNamespaceDecls();
        context.endAttributes();
        try {
            context.text(javax.xml.bind.DatatypeConverter.printBoolean(((boolean) _IsSuccess)), "IsSuccess");
        } catch (java.lang.Exception e) {
            com.vodafone.global.er.decoupling.binding.request.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endElement();
        context.startElement("", "package-subscription-id");
        context.endNamespaceDecls();
        context.endAttributes();
        try {
            context.text(((java.lang.String) _PackageSubscriptionId), "PackageSubscriptionId");
        } catch (java.lang.Exception e) {
            com.vodafone.global.er.decoupling.binding.request.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endElement();
        context.startElement("", "transaction-id");
        context.endNamespaceDecls();
        context.endAttributes();
        try {
            context.text(((java.lang.String) _TransactionId), "TransactionId");
        } catch (java.lang.Exception e) {
            com.vodafone.global.er.decoupling.binding.request.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endElement();
        context.startElement("", "reason-code");
        context.childAsURIs(((com.sun.xml.bind.JAXBObject) _ReasonCode), "ReasonCode");
        context.endNamespaceDecls();
        context.childAsAttributes(((com.sun.xml.bind.JAXBObject) _ReasonCode), "ReasonCode");
        context.endAttributes();
        context.childAsBody(((com.sun.xml.bind.JAXBObject) _ReasonCode), "ReasonCode");
        context.endElement();
        context.startElement("", "rate");
        context.endNamespaceDecls();
        context.endAttributes();
        try {
            context.text(javax.xml.bind.DatatypeConverter.printDouble(((double) _Rate)), "Rate");
        } catch (java.lang.Exception e) {
            com.vodafone.global.er.decoupling.binding.request.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endElement();
    }

    public void serializeAttributes(com.vodafone.global.er.decoupling.binding.request.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        if (!has_IsSuccess) {
            context.reportError(com.sun.xml.bind.serializer.Util.createMissingObjectError(this, "IsSuccess"));
        }
        if (!has_Rate) {
            context.reportError(com.sun.xml.bind.serializer.Util.createMissingObjectError(this, "Rate"));
        }
    }

    public void serializeURIs(com.vodafone.global.er.decoupling.binding.request.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        if (!has_IsSuccess) {
            context.reportError(com.sun.xml.bind.serializer.Util.createMissingObjectError(this, "IsSuccess"));
        }
        if (!has_Rate) {
            context.reportError(com.sun.xml.bind.serializer.Util.createMissingObjectError(this, "Rate"));
        }
    }

    public java.lang.Class getPrimaryInterface() {
        return (com.vodafone.global.er.decoupling.binding.request.PurchaseTariffAuthorisationType.class);
    }

    public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
        if (schemaFragment == null) {
            schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize((
 "\u00ac\u00ed\u0000\u0005sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.su"
+"n.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/gra"
+"mmar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expressi"
+"on\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0002L\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava/lang/Boolean;L\u0000\u000b"
+"expandedExpq\u0000~\u0000\u0002xpppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsr\u0000\'com.sun.msv."
+"grammar.trex.ElementPattern\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\tnameClasst\u0000\u001fLcom/su"
+"n/msv/grammar/NameClass;xr\u0000\u001ecom.sun.msv.grammar.ElementExp\u0000\u0000"
+"\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002Z\u0000\u001aignoreUndeclaredAttributesL\u0000\fcontentModelq\u0000~\u0000\u0002xq"
+"\u0000~\u0000\u0003pp\u0000sq\u0000~\u0000\u0000ppsr\u0000\u001bcom.sun.msv.grammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\u0002"
+"dtt\u0000\u001fLorg/relaxng/datatype/Datatype;L\u0000\u0006exceptq\u0000~\u0000\u0002L\u0000\u0004namet\u0000\u001d"
+"Lcom/sun/msv/util/StringPair;xq\u0000~\u0000\u0003ppsr\u0000$com.sun.msv.datatyp"
+"e.xsd.BooleanType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000*com.sun.msv.datatype.xsd.Bui"
+"ltinAtomicType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun.msv.datatype.xsd.Concre"
+"teType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\'com.sun.msv.datatype.xsd.XSDatatypeImpl"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\fnamespaceUrit\u0000\u0012Ljava/lang/String;L\u0000\btypeNameq\u0000"
+"~\u0000\u0016L\u0000\nwhiteSpacet\u0000.Lcom/sun/msv/datatype/xsd/WhiteSpaceProce"
+"ssor;xpt\u0000 http://www.w3.org/2001/XMLSchemat\u0000\u0007booleansr\u00005com."
+"sun.msv.datatype.xsd.WhiteSpaceProcessor$Collapse\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000"
+"xr\u0000,com.sun.msv.datatype.xsd.WhiteSpaceProcessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000x"
+"psr\u00000com.sun.msv.grammar.Expression$NullSetExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003sr\u0000\u0011java.lang.Boolean\u00cd r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000psr\u0000\u001bco"
+"m.sun.msv.util.StringPair\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u0016L\u0000\fname"
+"spaceURIq\u0000~\u0000\u0016xpq\u0000~\u0000\u001aq\u0000~\u0000\u0019sr\u0000\u001dcom.sun.msv.grammar.ChoiceExp\u0000\u0000"
+"\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0001ppsr\u0000 com.sun.msv.grammar.AttributeExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0001\u0002\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0002L\u0000\tnameClassq\u0000~\u0000\nxq\u0000~\u0000\u0003q\u0000~\u0000!psq\u0000~\u0000\u000eppsr\u0000\"com."
+"sun.msv.datatype.xsd.QnameType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0013q\u0000~\u0000\u0019t\u0000\u0005QName"
+"q\u0000~\u0000\u001dq\u0000~\u0000\u001fsq\u0000~\u0000\"q\u0000~\u0000+q\u0000~\u0000\u0019sr\u0000#com.sun.msv.grammar.SimpleName"
+"Class\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u0016L\u0000\fnamespaceURIq\u0000~\u0000\u0016xr\u0000\u001dcom"
+".sun.msv.grammar.NameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpt\u0000\u0004typet\u0000)http://www."
+"w3.org/2001/XMLSchema-instancesr\u00000com.sun.msv.grammar.Expres"
+"sion$EpsilonExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003sq\u0000~\u0000 \u0001psq\u0000~\u0000-t\u0000\nis-s"
+"uccesst\u0000\u0000sq\u0000~\u0000\tpp\u0000sq\u0000~\u0000\u0000ppsq\u0000~\u0000\u000eq\u0000~\u0000!psr\u0000#com.sun.msv.dataty"
+"pe.xsd.StringType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001Z\u0000\risAlwaysValidxq\u0000~\u0000\u0013q\u0000~\u0000\u0019t\u0000\u0006st"
+"ringsr\u00005com.sun.msv.datatype.xsd.WhiteSpaceProcessor$Preserv"
+"e\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u001c\u0001q\u0000~\u0000\u001fsq\u0000~\u0000\"q\u0000~\u0000=q\u0000~\u0000\u0019sq\u0000~\u0000$ppsq\u0000~\u0000&q\u0000~\u0000!p"
+"q\u0000~\u0000(q\u0000~\u0000/q\u0000~\u00003sq\u0000~\u0000-t\u0000\u0017package-subscription-idq\u0000~\u00007sq\u0000~\u0000\tpp"
+"\u0000sq\u0000~\u0000\u0000ppq\u0000~\u0000:sq\u0000~\u0000$ppsq\u0000~\u0000&q\u0000~\u0000!pq\u0000~\u0000(q\u0000~\u0000/q\u0000~\u00003sq\u0000~\u0000-t\u0000\u000etr"
+"ansaction-idq\u0000~\u00007sq\u0000~\u0000\tpp\u0000sq\u0000~\u0000\u0000ppsq\u0000~\u0000\tpp\u0000sq\u0000~\u0000$ppsr\u0000 com.s"
+"un.msv.grammar.OneOrMoreExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001ccom.sun.msv.gramma"
+"r.UnaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\u0003expq\u0000~\u0000\u0002xq\u0000~\u0000\u0003q\u0000~\u0000!psq\u0000~\u0000&q\u0000~\u0000!psr\u00002"
+"com.sun.msv.grammar.Expression$AnyStringExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000"
+"\u0000xq\u0000~\u0000\u0003q\u0000~\u00004psr\u0000 com.sun.msv.grammar.AnyNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000"
+"xq\u0000~\u0000.q\u0000~\u00003sq\u0000~\u0000-t\u0000@com.vodafone.global.er.decoupling.bindin"
+"g.request.ReasonCodeTypet\u0000+http://java.sun.com/jaxb/xjc/dumm"
+"y-elementssq\u0000~\u0000$ppsq\u0000~\u0000&q\u0000~\u0000!pq\u0000~\u0000(q\u0000~\u0000/q\u0000~\u00003sq\u0000~\u0000-t\u0000\u000breason"
+"-codeq\u0000~\u00007sq\u0000~\u0000\tpp\u0000sq\u0000~\u0000\u0000ppsq\u0000~\u0000\u000eppsr\u0000#com.sun.msv.datatype."
+"xsd.DoubleType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000+com.sun.msv.datatype.xsd.Floati"
+"ngNumberType\u00fc\u00e3\u00b6\u0087\u008c\u00a8|\u00e0\u0002\u0000\u0000xq\u0000~\u0000\u0013q\u0000~\u0000\u0019t\u0000\u0006doubleq\u0000~\u0000\u001dq\u0000~\u0000\u001fsq\u0000~\u0000\"q"
+"\u0000~\u0000dq\u0000~\u0000\u0019sq\u0000~\u0000$ppsq\u0000~\u0000&q\u0000~\u0000!pq\u0000~\u0000(q\u0000~\u0000/q\u0000~\u00003sq\u0000~\u0000-t\u0000\u0004rateq\u0000~"
+"\u00007sr\u0000\"com.sun.msv.grammar.ExpressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\bexpTab"
+"let\u0000/Lcom/sun/msv/grammar/ExpressionPool$ClosedHash;xpsr\u0000-co"
+"m.sun.msv.grammar.ExpressionPool$ClosedHash\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0003\u0000\u0003I\u0000\u0005cou"
+"ntB\u0000\rstreamVersionL\u0000\u0006parentt\u0000$Lcom/sun/msv/grammar/Expressio"
+"nPool;xp\u0000\u0000\u0000\u0010\u0001pq\u0000~\u0000\u0007q\u0000~\u00009q\u0000~\u0000Fq\u0000~\u0000Qq\u0000~\u0000Lq\u0000~\u0000Nq\u0000~\u0000\u0005q\u0000~\u0000%q\u0000~\u0000Aq"
+"\u0000~\u0000Gq\u0000~\u0000Zq\u0000~\u0000fq\u0000~\u0000\rq\u0000~\u0000\bq\u0000~\u0000_q\u0000~\u0000\u0006x"));
        }
        return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
    }

    public class Unmarshaller
        extends com.vodafone.global.er.decoupling.binding.request.impl.runtime.AbstractUnmarshallingEventHandlerImpl
    {


        public Unmarshaller(com.vodafone.global.er.decoupling.binding.request.impl.runtime.UnmarshallingContext context) {
            super(context, "----------------");
        }

        protected Unmarshaller(com.vodafone.global.er.decoupling.binding.request.impl.runtime.UnmarshallingContext context, int startState) {
            this(context);
            state = startState;
        }

        public java.lang.Object owner() {
            return com.vodafone.global.er.decoupling.binding.request.impl.PurchaseTariffAuthorisationTypeImpl.this;
        }

        public void enterElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname, org.xml.sax.Attributes __atts)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  6 :
                        if (("transaction-id" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 7;
                            return ;
                        }
                        break;
                    case  3 :
                        if (("package-subscription-id" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 4;
                            return ;
                        }
                        break;
                    case  9 :
                        if (("reason-code" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 10;
                            return ;
                        }
                        break;
                    case  15 :
                        revertToParentFromEnterElement(___uri, ___local, ___qname, __atts);
                        return ;
                    case  0 :
                        if (("is-success" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 1;
                            return ;
                        }
                        break;
                    case  10 :
                        if (("code" == ___local)&&("" == ___uri)) {
                            _ReasonCode = ((com.vodafone.global.er.decoupling.binding.request.impl.ReasonCodeTypeImpl) spawnChildFromEnterElement((com.vodafone.global.er.decoupling.binding.request.impl.ReasonCodeTypeImpl.class), 11, ___uri, ___local, ___qname, __atts));
                            return ;
                        }
                        break;
                    case  12 :
                        if (("rate" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 13;
                            return ;
                        }
                        break;
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
                    case  8 :
                        if (("transaction-id" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 9;
                            return ;
                        }
                        break;
                    case  5 :
                        if (("package-subscription-id" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 6;
                            return ;
                        }
                        break;
                    case  11 :
                        if (("reason-code" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 12;
                            return ;
                        }
                        break;
                    case  15 :
                        revertToParentFromLeaveElement(___uri, ___local, ___qname);
                        return ;
                    case  2 :
                        if (("is-success" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 3;
                            return ;
                        }
                        break;
                    case  14 :
                        if (("rate" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 15;
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
                    case  15 :
                        revertToParentFromEnterAttribute(___uri, ___local, ___qname);
                        return ;
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
                    case  15 :
                        revertToParentFromLeaveAttribute(___uri, ___local, ___qname);
                        return ;
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
                        case  4 :
                            state = 5;
                            eatText1(value);
                            return ;
                        case  7 :
                            state = 8;
                            eatText2(value);
                            return ;
                        case  1 :
                            state = 2;
                            eatText3(value);
                            return ;
                        case  13 :
                            state = 14;
                            eatText4(value);
                            return ;
                        case  15 :
                            revertToParentFromText(value);
                            return ;
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
                _PackageSubscriptionId = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText2(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _TransactionId = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText3(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _IsSuccess = javax.xml.bind.DatatypeConverter.parseBoolean(com.sun.xml.bind.WhiteSpaceProcessor.collapse(value));
                has_IsSuccess = true;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText4(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _Rate = javax.xml.bind.DatatypeConverter.parseDouble(com.sun.xml.bind.WhiteSpaceProcessor.collapse(value));
                has_Rate = true;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

    }

}