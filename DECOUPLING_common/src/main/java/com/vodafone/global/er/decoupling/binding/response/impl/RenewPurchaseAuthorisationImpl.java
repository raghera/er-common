//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.08.13 at 04:57:40 PM BST 
//


package com.vodafone.global.er.decoupling.binding.response.impl;

public class RenewPurchaseAuthorisationImpl
    extends com.vodafone.global.er.decoupling.binding.response.impl.RenewPurchaseAuthorisationTypeImpl
    implements com.vodafone.global.er.decoupling.binding.response.RenewPurchaseAuthorisation, com.sun.xml.bind.RIElement, com.sun.xml.bind.JAXBObject, com.vodafone.global.er.decoupling.binding.response.impl.runtime.UnmarshallableObject, com.vodafone.global.er.decoupling.binding.response.impl.runtime.XMLSerializable, com.vodafone.global.er.decoupling.binding.response.impl.runtime.ValidatableObject
{

    public final static java.lang.Class version = (com.vodafone.global.er.decoupling.binding.response.impl.JAXBVersion.class);
    private static com.sun.msv.grammar.Grammar schemaFragment;

    private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
        return (com.vodafone.global.er.decoupling.binding.response.RenewPurchaseAuthorisation.class);
    }

    public java.lang.String ____jaxb_ri____getNamespaceURI() {
        return "";
    }

    public java.lang.String ____jaxb_ri____getLocalName() {
        return "renew-purchase-authorisation";
    }

    public com.vodafone.global.er.decoupling.binding.response.impl.runtime.UnmarshallingEventHandler createUnmarshaller(com.vodafone.global.er.decoupling.binding.response.impl.runtime.UnmarshallingContext context) {
        return new com.vodafone.global.er.decoupling.binding.response.impl.RenewPurchaseAuthorisationImpl.Unmarshaller(context);
    }

    public void serializeBody(com.vodafone.global.er.decoupling.binding.response.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        context.startElement("", "renew-purchase-authorisation");
        super.serializeURIs(context);
        context.endNamespaceDecls();
        super.serializeAttributes(context);
        context.endAttributes();
        super.serializeBody(context);
        context.endElement();
    }

    public void serializeAttributes(com.vodafone.global.er.decoupling.binding.response.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
    }

    public void serializeURIs(com.vodafone.global.er.decoupling.binding.response.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
    }

    public java.lang.Class getPrimaryInterface() {
        return (com.vodafone.global.er.decoupling.binding.response.RenewPurchaseAuthorisation.class);
    }

    public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
        if (schemaFragment == null) {
            schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize((
 "\u00ac\u00ed\u0000\u0005sr\u0000\'com.sun.msv.grammar.trex.ElementPattern\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000"
+"\tnameClasst\u0000\u001fLcom/sun/msv/grammar/NameClass;xr\u0000\u001ecom.sun.msv."
+"grammar.ElementExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002Z\u0000\u001aignoreUndeclaredAttributesL\u0000"
+"\fcontentModelt\u0000 Lcom/sun/msv/grammar/Expression;xr\u0000\u001ecom.sun."
+"msv.grammar.Expression\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0002L\u0000\u0013epsilonReducibilityt\u0000\u0013Lj"
+"ava/lang/Boolean;L\u0000\u000bexpandedExpq\u0000~\u0000\u0003xppp\u0000sr\u0000\u001fcom.sun.msv.gra"
+"mmar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.sun.msv.grammar.BinaryExp"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1q\u0000~\u0000\u0003L\u0000\u0004exp2q\u0000~\u0000\u0003xq\u0000~\u0000\u0004ppsq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0007pps"
+"q\u0000~\u0000\u0007ppsq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0007ppsq\u0000~\u0000"
+"\u0007ppsq\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000\u0007ppsr\u0000\u001bcom.sun.msv.grammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002"
+"\u0000\u0003L\u0000\u0002dtt\u0000\u001fLorg/relaxng/datatype/Datatype;L\u0000\u0006exceptq\u0000~\u0000\u0003L\u0000\u0004na"
+"met\u0000\u001dLcom/sun/msv/util/StringPair;xq\u0000~\u0000\u0004ppsr\u0000$com.sun.msv.da"
+"tatype.xsd.BooleanType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000*com.sun.msv.datatype.xs"
+"d.BuiltinAtomicType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun.msv.datatype.xsd.C"
+"oncreteType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\'com.sun.msv.datatype.xsd.XSDatatyp"
+"eImpl\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\fnamespaceUrit\u0000\u0012Ljava/lang/String;L\u0000\btypeN"
+"ameq\u0000~\u0000\u001eL\u0000\nwhiteSpacet\u0000.Lcom/sun/msv/datatype/xsd/WhiteSpace"
+"Processor;xpt\u0000 http://www.w3.org/2001/XMLSchemat\u0000\u0007booleansr\u0000"
+"5com.sun.msv.datatype.xsd.WhiteSpaceProcessor$Collapse\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0000\u0001\u0002\u0000\u0000xr\u0000,com.sun.msv.datatype.xsd.WhiteSpaceProcessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0001\u0002\u0000\u0000xpsr\u00000com.sun.msv.grammar.Expression$NullSetExpression\u0000\u0000"
+"\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0004sr\u0000\u0011java.lang.Boolean\u00cd r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000ps"
+"r\u0000\u001bcom.sun.msv.util.StringPair\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u001eL\u0000"
+"\fnamespaceURIq\u0000~\u0000\u001expq\u0000~\u0000\"q\u0000~\u0000!sr\u0000\u001dcom.sun.msv.grammar.Choice"
+"Exp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\bppsr\u0000 com.sun.msv.grammar.AttributeExp\u0000\u0000"
+"\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0003L\u0000\tnameClassq\u0000~\u0000\u0001xq\u0000~\u0000\u0004q\u0000~\u0000)psq\u0000~\u0000\u0016ppsr\u0000"
+"\"com.sun.msv.datatype.xsd.QnameType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u001bq\u0000~\u0000!t\u0000\u0005"
+"QNameq\u0000~\u0000%q\u0000~\u0000\'sq\u0000~\u0000*q\u0000~\u00003q\u0000~\u0000!sr\u0000#com.sun.msv.grammar.Simpl"
+"eNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u001eL\u0000\fnamespaceURIq\u0000~\u0000\u001exr"
+"\u0000\u001dcom.sun.msv.grammar.NameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpt\u0000\u0004typet\u0000)http:/"
+"/www.w3.org/2001/XMLSchema-instancesr\u00000com.sun.msv.grammar.E"
+"xpression$EpsilonExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0004sq\u0000~\u0000(\u0001psq\u0000~\u00005t\u0000"
+"\u0010is-reserved-onlyt\u0000\u0000sq\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000\u0007ppq\u0000~\u0000\u0019sq\u0000~\u0000,ppsq\u0000~\u0000.q\u0000~\u0000"
+")pq\u0000~\u00000q\u0000~\u00007q\u0000~\u0000;sq\u0000~\u00005t\u0000\u000binteractiveq\u0000~\u0000?sq\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000\u0007ppq"
+"\u0000~\u0000\u0019sq\u0000~\u0000,ppsq\u0000~\u0000.q\u0000~\u0000)pq\u0000~\u00000q\u0000~\u00007q\u0000~\u0000;sq\u0000~\u00005t\u0000\nis-successq\u0000"
+"~\u0000?sq\u0000~\u0000,ppsr\u0000 com.sun.msv.grammar.OneOrMoreExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr"
+"\u0000\u001ccom.sun.msv.grammar.UnaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\u0003expq\u0000~\u0000\u0003xq\u0000~\u0000\u0004q\u0000"
+"~\u0000)psq\u0000~\u0000\u0000q\u0000~\u0000)p\u0000sq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000,ppsq\u0000~\u0000Mq\u0000~\u0000)psq\u0000~\u0000."
+"q\u0000~\u0000)psr\u00002com.sun.msv.grammar.Expression$AnyStringExpression"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0004q\u0000~\u0000<psr\u0000 com.sun.msv.grammar.AnyNameClass\u0000"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u00006q\u0000~\u0000;sq\u0000~\u00005t\u0000Fcom.vodafone.global.er.decoupl"
+"ing.binding.response.ResourceBalanceTypet\u0000+http://java.sun.c"
+"om/jaxb/xjc/dummy-elementssq\u0000~\u0000,ppsq\u0000~\u0000.q\u0000~\u0000)pq\u0000~\u00000q\u0000~\u00007q\u0000~\u0000"
+";sq\u0000~\u00005t\u0000\u0015user-resource-balanceq\u0000~\u0000?q\u0000~\u0000;sq\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000\u0007ppsq"
+"\u0000~\u0000\u0016q\u0000~\u0000)psr\u0000#com.sun.msv.datatype.xsd.StringType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001"
+"Z\u0000\risAlwaysValidxq\u0000~\u0000\u001bq\u0000~\u0000!t\u0000\u0006stringsr\u00005com.sun.msv.datatype"
+".xsd.WhiteSpaceProcessor$Preserve\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000$\u0001q\u0000~\u0000\'sq\u0000~"
+"\u0000*q\u0000~\u0000fq\u0000~\u0000!sq\u0000~\u0000,ppsq\u0000~\u0000.q\u0000~\u0000)pq\u0000~\u00000q\u0000~\u00007q\u0000~\u0000;sq\u0000~\u00005t\u0000\u0017pack"
+"age-subscription-idq\u0000~\u0000?sq\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000,ppsq"
+"\u0000~\u0000Mq\u0000~\u0000)psq\u0000~\u0000.q\u0000~\u0000)pq\u0000~\u0000Wq\u0000~\u0000Yq\u0000~\u0000;sq\u0000~\u00005t\u0000Acom.vodafone.g"
+"lobal.er.decoupling.binding.response.ReasonCodeTypeq\u0000~\u0000\\sq\u0000~"
+"\u0000,ppsq\u0000~\u0000.q\u0000~\u0000)pq\u0000~\u00000q\u0000~\u00007q\u0000~\u0000;sq\u0000~\u00005t\u0000\u000breason-codeq\u0000~\u0000?sq\u0000~"
+"\u0000\u0000pp\u0000sq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000,ppsq\u0000~\u0000Mq\u0000~\u0000)psq\u0000~\u0000.q\u0000~\u0000)pq\u0000~\u0000Wq"
+"\u0000~\u0000Yq\u0000~\u0000;sq\u0000~\u00005q\u0000~\u0000uq\u0000~\u0000\\sq\u0000~\u0000,ppsq\u0000~\u0000.q\u0000~\u0000)pq\u0000~\u00000q\u0000~\u00007q\u0000~\u0000;"
+"sq\u0000~\u00005t\u0000\u000fsub-reason-codeq\u0000~\u0000?sq\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000\u0007ppq\u0000~\u0000csq\u0000~\u0000,pps"
+"q\u0000~\u0000.q\u0000~\u0000)pq\u0000~\u00000q\u0000~\u00007q\u0000~\u0000;sq\u0000~\u00005t\u0000\u000etransaction-idq\u0000~\u0000?sq\u0000~\u0000\u0000"
+"pp\u0000sq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0016ppsr\u0000#com.sun.msv.datatype.xsd.DoubleType\u0000\u0000"
+"\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000+com.sun.msv.datatype.xsd.FloatingNumberType\u00fc\u00e3\u00b6\u0087"
+"\u008c\u00a8|\u00e0\u0002\u0000\u0000xq\u0000~\u0000\u001bq\u0000~\u0000!t\u0000\u0006doubleq\u0000~\u0000%q\u0000~\u0000\'sq\u0000~\u0000*q\u0000~\u0000\u0091q\u0000~\u0000!sq\u0000~\u0000,p"
+"psq\u0000~\u0000.q\u0000~\u0000)pq\u0000~\u00000q\u0000~\u00007q\u0000~\u0000;sq\u0000~\u00005t\u0000\u0004rateq\u0000~\u0000?sq\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000"
+"\u0007ppq\u0000~\u0000csq\u0000~\u0000,ppsq\u0000~\u0000.q\u0000~\u0000)pq\u0000~\u00000q\u0000~\u00007q\u0000~\u0000;sq\u0000~\u00005t\u0000\u0011error-de"
+"scriptionq\u0000~\u0000?sq\u0000~\u0000,ppsq\u0000~\u0000\u0000q\u0000~\u0000)p\u0000sq\u0000~\u0000\u0007ppq\u0000~\u0000csq\u0000~\u0000,ppsq\u0000~"
+"\u0000.q\u0000~\u0000)pq\u0000~\u00000q\u0000~\u00007q\u0000~\u0000;sq\u0000~\u00005t\u0000\berror-idq\u0000~\u0000?q\u0000~\u0000;sq\u0000~\u0000,ppsq"
+"\u0000~\u0000.q\u0000~\u0000)pq\u0000~\u00000q\u0000~\u00007q\u0000~\u0000;sq\u0000~\u00005t\u0000\u001crenew-purchase-authorisati"
+"onq\u0000~\u0000?sr\u0000\"com.sun.msv.grammar.ExpressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\be"
+"xpTablet\u0000/Lcom/sun/msv/grammar/ExpressionPool$ClosedHash;xps"
+"r\u0000-com.sun.msv.grammar.ExpressionPool$ClosedHash\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0003\u0000\u0003I"
+"\u0000\u0005countB\u0000\rstreamVersionL\u0000\u0006parentt\u0000$Lcom/sun/msv/grammar/Expr"
+"essionPool;xp\u0000\u0000\u0000+\u0001pq\u0000~\u0000Aq\u0000~\u0000Gq\u0000~\u0000~q\u0000~\u0000\fq\u0000~\u0000Qq\u0000~\u0000bq\u0000~\u0000oq\u0000~\u0000{q"
+"\u0000~\u0000\u0086q\u0000~\u0000\u0098q\u0000~\u0000\u009fq\u0000~\u0000Sq\u0000~\u0000qq\u0000~\u0000}q\u0000~\u0000\u0010q\u0000~\u0000-q\u0000~\u0000Bq\u0000~\u0000\u0013q\u0000~\u0000Hq\u0000~\u0000]q"
+"\u0000~\u0000jq\u0000~\u0000vq\u0000~\u0000\u0081q\u0000~\u0000\u0087q\u0000~\u0000\u0093q\u0000~\u0000\u0099q\u0000~\u0000\u00a0q\u0000~\u0000\u009dq\u0000~\u0000\u00a4q\u0000~\u0000\u008cq\u0000~\u0000\rq\u0000~\u0000\u0011q"
+"\u0000~\u0000\u0012q\u0000~\u0000\u000eq\u0000~\u0000\u000fq\u0000~\u0000\nq\u0000~\u0000Lq\u0000~\u0000\tq\u0000~\u0000Oq\u0000~\u0000\u000bq\u0000~\u0000Tq\u0000~\u0000rq\u0000~\u0000\u0015x"));
        }
        return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
    }

    public class Unmarshaller
        extends com.vodafone.global.er.decoupling.binding.response.impl.runtime.AbstractUnmarshallingEventHandlerImpl
    {


        public Unmarshaller(com.vodafone.global.er.decoupling.binding.response.impl.runtime.UnmarshallingContext context) {
            super(context, "----");
        }

        protected Unmarshaller(com.vodafone.global.er.decoupling.binding.response.impl.runtime.UnmarshallingContext context, int startState) {
            this(context);
            state = startState;
        }

        public java.lang.Object owner() {
            return com.vodafone.global.er.decoupling.binding.response.impl.RenewPurchaseAuthorisationImpl.this;
        }

        public void enterElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname, org.xml.sax.Attributes __atts)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  0 :
                        if (("renew-purchase-authorisation" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 1;
                            return ;
                        }
                        break;
                    case  3 :
                        revertToParentFromEnterElement(___uri, ___local, ___qname, __atts);
                        return ;
                    case  1 :
                        if (("is-reserved-only" == ___local)&&("" == ___uri)) {
                            spawnHandlerFromEnterElement((((com.vodafone.global.er.decoupling.binding.response.impl.RenewPurchaseAuthorisationTypeImpl)com.vodafone.global.er.decoupling.binding.response.impl.RenewPurchaseAuthorisationImpl.this).new Unmarshaller(context)), 2, ___uri, ___local, ___qname, __atts);
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
                    case  2 :
                        if (("renew-purchase-authorisation" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 3;
                            return ;
                        }
                        break;
                    case  3 :
                        revertToParentFromLeaveElement(___uri, ___local, ___qname);
                        return ;
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
                    case  3 :
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
                    case  3 :
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
                        case  3 :
                            revertToParentFromText(value);
                            return ;
                    }
                } catch (java.lang.RuntimeException e) {
                    handleUnexpectedTextException(value, e);
                }
                break;
            }
        }

    }

}
