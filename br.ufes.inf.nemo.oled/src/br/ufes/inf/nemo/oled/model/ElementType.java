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
  
  // FOPs
  PATTERN_COMPLETER, FOP_PRINCIPLE_OF_IDENTITY, FOP_MULTIPLE_GENERIC_RELATOR, FOP_GENERIC_RELATOR, FOP_GENERAL_RELATOR, FOP_ROLEMIXIN_DEPENDENCE, 
  FOP_PARTITION_ROLE, FOP_PARTITION_PHASE, FOP_PARTITION_SUBKIND, FOP_PARTITION_CATEGORY, FOP_PARTITION_ROLEMIXIN, FOP_PARTITION_MODE,
  FOP_PARTITION_RELATOR, FOP_GENERALIZATION_CATEGORY, FOP_GENERALIZATION_MIXIN, FOP_GENERALIZATION_ROLEMIXIN,FOP_RELATION_FORMAL, FOP_RELATION_CHARACTERIZATION,
  
  // Missing Types
  ENUMERATION, PRIMITIVETYPE, PERCEIVABLEQUALITY, NONPERCEIVABLEQUALITY, NOMINALQUALITY,
  // Domain Patterns
  DOMAIN_PATTERN
}
