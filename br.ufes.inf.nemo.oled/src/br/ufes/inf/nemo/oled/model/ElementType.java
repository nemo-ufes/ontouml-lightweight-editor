/**
 * Copyright(C) 2011-2014 by Victor Amorim, John Guerson, Tiago Prince, Antognoni Albuquerque
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * license terms.
 *
 * OLED is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * OLED is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OLED; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.ufes.inf.nemo.oled.model;

/**
 * The possible element types.
 * @author Antognoni Albuquerque
 */
public enum ElementType {
  // Types
  KIND, QUANTITY, COLLECTIVE, SUBKIND, PHASE, ROLE, 
  CATEGORY, ROLEMIXIN, MIXIN, MODE, RELATOR, DATATYPE, NOTE, PACKAGE, GENERALIZATIONSET, 
  COMMENT, CONSTRAINT, 
  // Derived Patterns
  UNION, EXCLUSION, INTERSECTION, SPECIALIZATION, PASTSPECIALIZATION, PARTICIPATION,
  // Patterns
  GENERALIZATIONSPECIALIZATION, PARTITIONPATTERN, ADDSUPERTYPE, ADDSUBTYPE,
  PATTERN_MIXIN_PATTERN, PATTERN_MIXIN_PATTERN_WITH_SUBKIND,PATTERN_PHASE_PARTITION, PATTERN_SUBKIND_PARTITION, PATTERN_ROLE_PARTITION, PATTERN_SUBSTANCE_SORTAL_PARTITION, PATTERN_ROLEMIXIN_PATTERN, PATTERN_RELATOR, PATTERN_COMPLETER, PATTERN_PRINCIPLE_IDENTITY,
  // Missing Types
  ENUMERATION, PRIMITIVETYPE, PERCEIVABLEQUALITY, NONPERCEIVABLEQUALITY, NOMINALQUALITY,
  // Domain Patterns
  DOMAIN_PATTERN
}
