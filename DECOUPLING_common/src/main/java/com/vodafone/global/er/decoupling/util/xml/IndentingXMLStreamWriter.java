/*
 * Copyright 2005-2006 Sun Microsystems, Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa Clara,
 * CA 95054 USA or visit www.sun.com if you need additional information or
 * have any questions.
 */
package com.vodafone.global.er.decoupling.util.xml;

import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import java.util.Stack;

/**
 * copied from com/sun/xml/internal/txw2/output/IndentingXMLStreamWriter
 * @author Kohsuke Kawaguchi
 */
public class IndentingXMLStreamWriter implements XMLStreamWriter {
    private final static Object SEEN_NOTHING = new Object();
    private final static Object SEEN_ELEMENT = new Object();
    private final static Object SEEN_DATA = new Object();

    private Object state = SEEN_NOTHING;
    private Stack<Object> stateStack = new Stack<>();

    private String indentStep = "  ";
    private int depth = 0;
	private XMLStreamWriter	writer;

    public IndentingXMLStreamWriter(XMLStreamWriter writer) {
        this.writer =writer;
    }

    /**
     * Return the current indent step.
     *
     * <p>Return the current indent step: each start tag will be
     * indented by this number of spaces times the number of
     * ancestors that the element has.</p>
     *
     * @return The number of spaces in each indentation step,
     *         or 0 or less for no indentation.
     * @see #setIndentStep(int)
     *
     * @deprecated
     *      Only return the length of the indent string.
     */
    public int getIndentStep() {
        return indentStep.length();
    }


    /**
     * Set the current indent step.
     *
     * @param indentStep The new indent step (0 or less for no
     *        indentation).
     * @see #getIndentStep()
     *
     * @deprecated
     *      Should use the version that takes string.
     */
    public void setIndentStep(int indentStep) {
        StringBuilder s = new StringBuilder();
        for (; indentStep > 0; indentStep--) s.append(' ');
        setIndentStep(s.toString());
    }

    public void setIndentStep(String s) {
        this.indentStep = s;
    }

    private void onStartElement() throws XMLStreamException {
        stateStack.push(SEEN_ELEMENT);
        state = SEEN_NOTHING;
        if (depth > 0) {
            writer.writeCharacters("\n");
        }
        doIndent();
        depth++;
    }

    private void onEndElement() throws XMLStreamException {
        depth--;
        if (state == SEEN_ELEMENT) {
            writer.writeCharacters("\n");
            doIndent();
        }
        state = stateStack.pop();
    }

    private void onEmptyElement() throws XMLStreamException {
        state = SEEN_ELEMENT;
        if (depth > 0) {
            writer.writeCharacters("\n");
        }
        doIndent();
    }

    /**
     * Print indentation for the current level.
     *
     * @exception org.xml.sax.SAXException If there is an error
     *            writing the indentation characters, or if a filter
     *            further down the chain raises an exception.
     */
    private void doIndent() throws XMLStreamException {
        if (depth > 0) {
            for (int i = 0; i < depth; i++)
                writer.writeCharacters(indentStep);
        }
    }


    public void writeStartDocument() throws XMLStreamException {
        writer.writeStartDocument();
        writer.writeCharacters("\n");
    }

    public void writeStartDocument(String version) throws XMLStreamException {
        writer.writeStartDocument(version);
        writer.writeCharacters("\n");
    }

    public void writeStartDocument(String encoding, String version) throws XMLStreamException {
        writer.writeStartDocument(encoding, version);
        writer.writeCharacters("\n");
    }

    public void writeStartElement(String localName) throws XMLStreamException {
        onStartElement();
        writer.writeStartElement(localName);
    }

    public void writeStartElement(String namespaceURI, String localName) throws XMLStreamException {
        onStartElement();
        writer.writeStartElement(namespaceURI, localName);
    }

    public void writeStartElement(String prefix, String localName, String namespaceURI) throws XMLStreamException {
        onStartElement();
        writer.writeStartElement(prefix, localName, namespaceURI);
    }

    public void writeEmptyElement(String namespaceURI, String localName) throws XMLStreamException {
        onEmptyElement();
        writer.writeEmptyElement(namespaceURI, localName);
    }

    public void writeEmptyElement(String prefix, String localName, String namespaceURI) throws XMLStreamException {
        onEmptyElement();
        writer.writeEmptyElement(prefix, localName, namespaceURI);
    }

    public void writeEmptyElement(String localName) throws XMLStreamException {
        onEmptyElement();
        writer.writeEmptyElement(localName);
    }

    public void writeEndElement() throws XMLStreamException {
        onEndElement();
        writer.writeEndElement();
    }

    public void writeCharacters(String text) throws XMLStreamException {
        state = SEEN_DATA;
        writer.writeCharacters(text);
    }

    public void writeCharacters(char[] text, int start, int len) throws XMLStreamException {
        state = SEEN_DATA;
        writer.writeCharacters(text, start, len);
    }

    public void writeCData(String data) throws XMLStreamException {
        state = SEEN_DATA;
        writer.writeCData(data);
    }
    
    public void writeDTD(String dtd) throws XMLStreamException {
        writer.writeDTD(dtd);
    }

    public void writeEntityRef(String name) throws XMLStreamException {
        writer.writeEntityRef(name);
    }

  

    public String getPrefix(String uri) throws XMLStreamException {
        return writer.getPrefix(uri);
    }

    public void setPrefix(String prefix, String uri) throws XMLStreamException {
        writer.setPrefix(prefix, uri);
    }

    public void setDefaultNamespace(String uri) throws XMLStreamException {
        writer.setDefaultNamespace(uri);
    }

    public void setNamespaceContext(NamespaceContext context) throws XMLStreamException {
        writer.setNamespaceContext(context);
    }

    public NamespaceContext getNamespaceContext() {
        return writer.getNamespaceContext();
    }

    public Object getProperty(String name) throws IllegalArgumentException {
        return writer.getProperty(name);
    }
    
    public void writeComment(String data) throws XMLStreamException {
        writer.writeComment(data);
    }

    public void writeProcessingInstruction(String target) throws XMLStreamException {
        writer.writeProcessingInstruction(target);
    }

    public void writeProcessingInstruction(String target, String data) throws XMLStreamException {
        writer.writeProcessingInstruction(target, data);
    }
    
    public void close() throws XMLStreamException {
        writer.close();
    }

    public void flush() throws XMLStreamException {
        writer.flush();
    }

    public void writeEndDocument() throws XMLStreamException {
        writer.writeEndDocument();
    }
    
    public void writeAttribute(String localName, String value) throws XMLStreamException {
        writer.writeAttribute(localName, value);
    }

    public void writeAttribute(String prefix, String namespaceURI, String localName, String value) throws XMLStreamException {
        writer.writeAttribute(prefix, namespaceURI, localName, value);
    }

    public void writeAttribute(String namespaceURI, String localName, String value) throws XMLStreamException {
        writer.writeAttribute(namespaceURI, localName, value);
    }

    public void writeNamespace(String prefix, String namespaceURI) throws XMLStreamException {
        writer.writeNamespace(prefix, namespaceURI);
    }

    public void writeDefaultNamespace(String namespaceURI) throws XMLStreamException {
        writer.writeDefaultNamespace(namespaceURI);
    }
}