//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.08.13 at 04:57:40 PM BST 
//


package com.vodafone.global.er.decoupling.binding.response.impl;

public class GoodwillCreditResponseTypeImpl implements com.vodafone.global.er.decoupling.binding.response.GoodwillCreditResponseType, com.sun.xml.bind.JAXBObject, com.vodafone.global.er.decoupling.binding.response.impl.runtime.UnmarshallableObject, com.vodafone.global.er.decoupling.binding.response.impl.runtime.XMLSerializable, com.vodafone.global.er.decoupling.binding.response.impl.runtime.ValidatableObject
{

    protected java.lang.String _TransactionId;
    protected boolean has_IsSuccess;
    protected boolean _IsSuccess;
    protected java.lang.String _SubscriptionId;
    protected com.vodafone.global.er.decoupling.binding.response.ReasonCodeType _ReasonCode;
    public final static java.lang.Class version = (com.vodafone.global.er.decoupling.binding.response.impl.JAXBVersion.class);
    private static com.sun.msv.grammar.Grammar schemaFragment;

    private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
        return (com.vodafone.global.er.decoupling.binding.response.GoodwillCreditResponseType.class);
    }

    public java.lang.String getTransactionId() {
        return _TransactionId;
    }

    public void setTransactionId(java.lang.String value) {
        _TransactionId = value;
    }

    public boolean isIsSuccess() {
        return _IsSuccess;
    }

    public void setIsSuccess(boolean value) {
        _IsSuccess = value;
        has_IsSuccess = true;
    }

    public java.lang.String getSubscriptionId() {
        return _SubscriptionId;
    }

    public void setSubscriptionId(java.lang.String value) {
        _SubscriptionId = value;
    }

    public com.vodafone.global.er.decoupling.binding.response.ReasonCodeType getReasonCode() {
        return _ReasonCode;
    }

    public void setReasonCode(com.vodafone.global.er.decoupling.binding.response.ReasonCodeType value) {
        _ReasonCode = value;
    }

    public com.vodafone.global.er.decoupling.binding.response.impl.runtime.UnmarshallingEventHandler createUnmarshaller(com.vodafone.global.er.decoupling.binding.response.impl.runtime.UnmarshallingContext context) {
        return new com.vodafone.global.er.decoupling.binding.response.impl.GoodwillCreditResponseTypeImpl.Unmarshaller(context);
    }

    public void serializeBody(com.vodafone.global.er.decoupling.binding.response.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        if (!has_IsSuccess) {
            context.reportError(com.sun.xml.bind.serializer.Util.createMissingObjectError(this, "IsSuccess"));
        }
        context.startElement("", "is-success");
        context.endNamespaceDecls();
        context.endAttributes();
        try {
            context.text(javax.xml.bind.DatatypeConverter.printBoolean(((boolean) _IsSuccess)), "IsSuccess");
        } catch (java.lang.Exception e) {
            com.vodafone.global.er.decoupling.binding.response.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endElement();
        if (_TransactionId!= null) {
            context.startElement("", "transaction-id");
            context.endNamespaceDecls();
            context.endAttributes();
            try {
                context.text(((java.lang.String) _TransactionId), "TransactionId");
            } catch (java.lang.Exception e) {
                com.vodafone.global.er.decoupling.binding.response.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endElement();
        }
        if (_SubscriptionId!= null) {
            context.startElement("", "subscription-id");
            context.endNamespaceDecls();
            context.endAttributes();
            try {
                context.text(((java.lang.String) _SubscriptionId), "SubscriptionId");
            } catch (java.lang.Exception e) {
                com.vodafone.global.er.decoupling.binding.response.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endElement();
        }
        context.startElement("", "reason-code");
        context.childAsURIs(((com.sun.xml.bind.JAXBObject) _ReasonCode), "ReasonCode");
        context.endNamespaceDecls();
        context.childAsAttributes(((com.sun.xml.bind.JAXBObject) _ReasonCode), "ReasonCode");
        context.endAttributes();
        context.childAsBody(((com.sun.xml.bind.JAXBObject) _ReasonCode), "ReasonCode");
        context.endElement();
    }

    public void serializeAttributes(com.vodafone.global.er.decoupling.binding.response.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        if (!has_IsSuccess) {
            context.reportError(com.sun.xml.bind.serializer.Util.createMissingObjectError(this, "IsSuccess"));
        }
    }

    public void serializeURIs(com.vodafone.global.er.decoupling.binding.response.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        if (!has_IsSuccess) {
            context.reportError(com.sun.xml.bind.serializer.Util.createMissingObjectError(this, "IsSuccess"));
        }
    }

    public java.lang.Class getPrimaryInterface() {
        return (com.vodafone.global.er.decoupling.binding.response.GoodwillCreditResponseType.class);
    }

    public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
        if (schemaFragment == null) {
            schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize((
 "\u00ac\u00ed\u0000\u0005sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.su"
+"n.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/gra"
+"mmar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expressi"
+"on\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0002L\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava/lang/Boolean;L\u0000\u000b"
+"expandedExpq\u0000~\u0000\u0002xpppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsr\u0000\'com.sun.msv.grammar."
+"trex.ElementPattern\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\tnameClasst\u0000\u001fLcom/sun/msv/gr"
+"ammar/NameClass;xr\u0000\u001ecom.sun.msv.grammar.ElementExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000"
+"\u0002Z\u0000\u001aignoreUndeclaredAttributesL\u0000\fcontentModelq\u0000~\u0000\u0002xq\u0000~\u0000\u0003pp\u0000s"
+"q\u0000~\u0000\u0000ppsr\u0000\u001bcom.sun.msv.grammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000\u001fLor"
+"g/relaxng/datatype/Datatype;L\u0000\u0006exceptq\u0000~\u0000\u0002L\u0000\u0004namet\u0000\u001dLcom/sun"
+"/msv/util/StringPair;xq\u0000~\u0000\u0003ppsr\u0000$com.sun.msv.datatype.xsd.Bo"
+"oleanType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000*com.sun.msv.datatype.xsd.BuiltinAtom"
+"icType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun.msv.datatype.xsd.ConcreteType\u0000\u0000"
+"\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\'com.sun.msv.datatype.xsd.XSDatatypeImpl\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001"
+"\u0002\u0000\u0003L\u0000\fnamespaceUrit\u0000\u0012Ljava/lang/String;L\u0000\btypeNameq\u0000~\u0000\u0015L\u0000\nwh"
+"iteSpacet\u0000.Lcom/sun/msv/datatype/xsd/WhiteSpaceProcessor;xpt"
+"\u0000 http://www.w3.org/2001/XMLSchemat\u0000\u0007booleansr\u00005com.sun.msv."
+"datatype.xsd.WhiteSpaceProcessor$Collapse\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000,com."
+"sun.msv.datatype.xsd.WhiteSpaceProcessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpsr\u00000com"
+".sun.msv.grammar.Expression$NullSetExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~"
+"\u0000\u0003sr\u0000\u0011java.lang.Boolean\u00cd r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000psr\u0000\u001bcom.sun.ms"
+"v.util.StringPair\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u0015L\u0000\fnamespaceURI"
+"q\u0000~\u0000\u0015xpq\u0000~\u0000\u0019q\u0000~\u0000\u0018sr\u0000\u001dcom.sun.msv.grammar.ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000"
+"\u0000xq\u0000~\u0000\u0001ppsr\u0000 com.sun.msv.grammar.AttributeExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003e"
+"xpq\u0000~\u0000\u0002L\u0000\tnameClassq\u0000~\u0000\txq\u0000~\u0000\u0003q\u0000~\u0000 psq\u0000~\u0000\rppsr\u0000\"com.sun.msv."
+"datatype.xsd.QnameType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0012q\u0000~\u0000\u0018t\u0000\u0005QNameq\u0000~\u0000\u001cq\u0000~"
+"\u0000\u001esq\u0000~\u0000!q\u0000~\u0000*q\u0000~\u0000\u0018sr\u0000#com.sun.msv.grammar.SimpleNameClass\u0000\u0000\u0000"
+"\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u0015L\u0000\fnamespaceURIq\u0000~\u0000\u0015xr\u0000\u001dcom.sun.msv"
+".grammar.NameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpt\u0000\u0004typet\u0000)http://www.w3.org/2"
+"001/XMLSchema-instancesr\u00000com.sun.msv.grammar.Expression$Eps"
+"ilonExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003sq\u0000~\u0000\u001f\u0001psq\u0000~\u0000,t\u0000\nis-successt\u0000"
+"\u0000sq\u0000~\u0000#ppsq\u0000~\u0000\bq\u0000~\u0000 p\u0000sq\u0000~\u0000\u0000ppsq\u0000~\u0000\rq\u0000~\u0000 psr\u0000#com.sun.msv.da"
+"tatype.xsd.StringType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001Z\u0000\risAlwaysValidxq\u0000~\u0000\u0012q\u0000~\u0000\u0018t"
+"\u0000\u0006stringsr\u00005com.sun.msv.datatype.xsd.WhiteSpaceProcessor$Pre"
+"serve\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u001b\u0001q\u0000~\u0000\u001esq\u0000~\u0000!q\u0000~\u0000=q\u0000~\u0000\u0018sq\u0000~\u0000#ppsq\u0000~\u0000%q\u0000"
+"~\u0000 pq\u0000~\u0000\'q\u0000~\u0000.q\u0000~\u00002sq\u0000~\u0000,t\u0000\u000etransaction-idq\u0000~\u00006q\u0000~\u00002sq\u0000~\u0000#pp"
+"sq\u0000~\u0000\bq\u0000~\u0000 p\u0000sq\u0000~\u0000\u0000ppq\u0000~\u0000:sq\u0000~\u0000#ppsq\u0000~\u0000%q\u0000~\u0000 pq\u0000~\u0000\'q\u0000~\u0000.q\u0000~\u0000"
+"2sq\u0000~\u0000,t\u0000\u000fsubscription-idq\u0000~\u00006q\u0000~\u00002sq\u0000~\u0000\bpp\u0000sq\u0000~\u0000\u0000ppsq\u0000~\u0000\bpp"
+"\u0000sq\u0000~\u0000#ppsr\u0000 com.sun.msv.grammar.OneOrMoreExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001c"
+"com.sun.msv.grammar.UnaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\u0003expq\u0000~\u0000\u0002xq\u0000~\u0000\u0003q\u0000~\u0000"
+" psq\u0000~\u0000%q\u0000~\u0000 psr\u00002com.sun.msv.grammar.Expression$AnyStringEx"
+"pression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003q\u0000~\u00003psr\u0000 com.sun.msv.grammar.AnyNa"
+"meClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000-q\u0000~\u00002sq\u0000~\u0000,t\u0000Acom.vodafone.global.er"
+".decoupling.binding.response.ReasonCodeTypet\u0000+http://java.su"
+"n.com/jaxb/xjc/dummy-elementssq\u0000~\u0000#ppsq\u0000~\u0000%q\u0000~\u0000 pq\u0000~\u0000\'q\u0000~\u0000.q"
+"\u0000~\u00002sq\u0000~\u0000,t\u0000\u000breason-codeq\u0000~\u00006sr\u0000\"com.sun.msv.grammar.Express"
+"ionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\bexpTablet\u0000/Lcom/sun/msv/grammar/Express"
+"ionPool$ClosedHash;xpsr\u0000-com.sun.msv.grammar.ExpressionPool$"
+"ClosedHash\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0003\u0000\u0003I\u0000\u0005countB\u0000\rstreamVersionL\u0000\u0006parentt\u0000$Lco"
+"m/sun/msv/grammar/ExpressionPool;xp\u0000\u0000\u0000\u000f\u0001pq\u0000~\u0000\u0005q\u0000~\u0000Mq\u0000~\u00009q\u0000~\u0000"
+"Gq\u0000~\u0000\u0007q\u0000~\u0000Oq\u0000~\u0000$q\u0000~\u0000Aq\u0000~\u00007q\u0000~\u0000Hq\u0000~\u0000Eq\u0000~\u0000\u0006q\u0000~\u0000[q\u0000~\u0000Rq\u0000~\u0000\fx"));
        }
        return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
    }

    public class Unmarshaller
        extends com.vodafone.global.er.decoupling.binding.response.impl.runtime.AbstractUnmarshallingEventHandlerImpl
    {


        public Unmarshaller(com.vodafone.global.er.decoupling.binding.response.impl.runtime.UnmarshallingContext context) {
            super(context, "-------------");
        }

        protected Unmarshaller(com.vodafone.global.er.decoupling.binding.response.impl.runtime.UnmarshallingContext context, int startState) {
            this(context);
            state = startState;
        }

        public java.lang.Object owner() {
            return com.vodafone.global.er.decoupling.binding.response.impl.GoodwillCreditResponseTypeImpl.this;
        }

        public void enterElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname, org.xml.sax.Attributes __atts)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  0 :
                        if (("is-success" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 1;
                            return ;
                        }
                        break;
                    case  12 :
                        revertToParentFromEnterElement(___uri, ___local, ___qname, __atts);
                        return ;
                    case  3 :
                        if (("transaction-id" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 4;
                            return ;
                        }
                        state = 6;
                        continue outer;
                    case  6 :
                        if (("subscription-id" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 7;
                            return ;
                        }
                        state = 9;
                        continue outer;
                    case  9 :
                        if (("reason-code" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 10;
                            return ;
                        }
                        break;
                    case  10 :
                        if (("code" == ___local)&&("" == ___uri)) {
                            _ReasonCode = ((com.vodafone.global.er.decoupling.binding.response.impl.ReasonCodeTypeImpl) spawnChildFromEnterElement((com.vodafone.global.er.decoupling.binding.response.impl.ReasonCodeTypeImpl.class), 11, ___uri, ___local, ___qname, __atts));
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
                    case  5 :
                        if (("transaction-id" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 6;
                            return ;
                        }
                        break;
                    case  12 :
                        revertToParentFromLeaveElement(___uri, ___local, ___qname);
                        return ;
                    case  8 :
                        if (("subscription-id" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 9;
                            return ;
                        }
                        break;
                    case  2 :
                        if (("is-success" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 3;
                            return ;
                        }
                        break;
                    case  3 :
                        state = 6;
                        continue outer;
                    case  11 :
                        if (("reason-code" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 12;
                            return ;
                        }
                        break;
                    case  6 :
                        state = 9;
                        continue outer;
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
                    case  12 :
                        revertToParentFromEnterAttribute(___uri, ___local, ___qname);
                        return ;
                    case  3 :
                        state = 6;
                        continue outer;
                    case  6 :
                        state = 9;
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
                    case  12 :
                        revertToParentFromLeaveAttribute(___uri, ___local, ___qname);
                        return ;
                    case  3 :
                        state = 6;
                        continue outer;
                    case  6 :
                        state = 9;
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
                        case  7 :
                            state = 8;
                            eatText1(value);
                            return ;
                        case  12 :
                            revertToParentFromText(value);
                            return ;
                        case  3 :
                            state = 6;
                            continue outer;
                        case  4 :
                            state = 5;
                            eatText2(value);
                            return ;
                        case  1 :
                            state = 2;
                            eatText3(value);
                            return ;
                        case  6 :
                            state = 9;
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
                _SubscriptionId = value;
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

    }

}
