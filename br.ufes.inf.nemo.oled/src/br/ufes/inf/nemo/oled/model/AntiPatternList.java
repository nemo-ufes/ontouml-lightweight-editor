/**
 * Copyright(C) 2011-2014 by John Guerson, Tiago Prince, Antognoni Albuquerque
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

import java.util.ArrayList;

import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.antipattern.GSRig.GSRigAntipattern;
import br.ufes.inf.nemo.antipattern.asscyc.AssCycAntipattern;
import br.ufes.inf.nemo.antipattern.binover.BinOverAntipattern;
import br.ufes.inf.nemo.antipattern.decint.DecIntAntipattern;
import br.ufes.inf.nemo.antipattern.depphase.DepPhaseAntipattern;
import br.ufes.inf.nemo.antipattern.freerole.FreeRoleAntipattern;
import br.ufes.inf.nemo.antipattern.hetcoll.HetCollAntipattern;
import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncAntipattern;
import br.ufes.inf.nemo.antipattern.impabs.ImpAbsAntipattern;
import br.ufes.inf.nemo.antipattern.mixiden.MixIdenAntipattern;
import br.ufes.inf.nemo.antipattern.mixrig.MixRigAntipattern;
import br.ufes.inf.nemo.antipattern.multidep.MultiDepAntipattern;
import br.ufes.inf.nemo.antipattern.partover.PartOverAntipattern;
import br.ufes.inf.nemo.antipattern.relcomp.RelCompAntipattern;
import br.ufes.inf.nemo.antipattern.relover.RelOverAntipattern;
import br.ufes.inf.nemo.antipattern.relrig.RelRigAntipattern;
import br.ufes.inf.nemo.antipattern.relspec.RelSpecAntipattern;
import br.ufes.inf.nemo.antipattern.reprel.RepRelAntipattern;
import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalAntipattern;
import br.ufes.inf.nemo.antipattern.undefphase.UndefPhaseAntipattern;
import br.ufes.inf.nemo.antipattern.wholeover.WholeOverAntipattern;

/**
 * 
 * This class represents a AntiPattern List Model.
 * 
 * @author John Guerson
 */

public class AntiPatternList {
	
	private AssCycAntipattern assCyc; 	
	private BinOverAntipattern binOver;		
	private DepPhaseAntipattern depPhase;
	private FreeRoleAntipattern freeRole;
	private GSRigAntipattern gsRig;
	private HetCollAntipattern hetColl;
	private HomoFuncAntipattern homoFunc;
	private ImpAbsAntipattern impAbs;
	private MixIdenAntipattern mixIden;
	private MixRigAntipattern mixRig;
	private MultiDepAntipattern multiDep;
	private RelCompAntipattern relComp;
	private RelOverAntipattern relOver;
	private RelRigAntipattern relRig;
	private RelSpecAntipattern relSpec;
	private RepRelAntipattern repRel;
	private UndefFormalAntipattern undefFormal;
	private UndefPhaseAntipattern undefPhase;
	private WholeOverAntipattern wholeOver;
	private PartOverAntipattern partOver;
	private DecIntAntipattern decInt;
	
	public AssCycAntipattern getAssCyc() {
		return assCyc;
	}
	public AntiPatternList(AssCycAntipattern assCyc,
			BinOverAntipattern binOver, DepPhaseAntipattern depPhase, FreeRoleAntipattern freeRole, GSRigAntipattern gsRig,
			HetCollAntipattern hetColl, HomoFuncAntipattern homoFunc, ImpAbsAntipattern impAbs,
			MixIdenAntipattern mixIden, MixRigAntipattern mixRig, MultiDepAntipattern multiDep, RelCompAntipattern relComp,
			RelOverAntipattern relOver, RelRigAntipattern relRig, RelSpecAntipattern relSpec, RepRelAntipattern repRel,
			UndefFormalAntipattern undefFormal, UndefPhaseAntipattern undefPhase, WholeOverAntipattern wholeOver,
			PartOverAntipattern partOver, DecIntAntipattern decInt) {

		this.assCyc = assCyc;
		this.binOver = binOver;
		this.depPhase = depPhase;
		this.freeRole = freeRole;
		this.gsRig = gsRig;
		this.hetColl = hetColl;
		this.homoFunc = homoFunc;
		this.impAbs = impAbs;
		this.mixIden = mixIden;
		this.mixRig = mixRig;
		this.multiDep = multiDep;
		this.relComp = relComp;
		this.relOver = relOver;
		this.relRig = relRig;
		this.relSpec = relSpec;
		this.repRel = repRel;
		this.undefFormal = undefFormal;
		this.undefPhase = undefPhase;
		this.wholeOver = wholeOver;
		this.partOver = partOver;
		this.decInt = decInt;
	}
	
	public AntiPatternList() {

	}
	
	public ArrayList<AntipatternOccurrence> getAll()
	{
		ArrayList<AntipatternOccurrence> result = new ArrayList<AntipatternOccurrence>();
		result.addAll(assCyc.getOccurrences());
		result.addAll(binOver.getOccurrences());
		result.addAll(depPhase.getOccurrences());
		result.addAll(freeRole.getOccurrences());
		result.addAll(gsRig.getOccurrences());
		result.addAll(hetColl.getOccurrences());
		result.addAll(homoFunc.getOccurrences());
		result.addAll(impAbs.getOccurrences());
		result.addAll(mixIden.getOccurrences());
		result.addAll(mixRig.getOccurrences());
		result.addAll(multiDep.getOccurrences());
		result.addAll(relComp.getOccurrences());
		result.addAll(relOver.getOccurrences());
		result.addAll(relRig.getOccurrences());
		result.addAll(relSpec.getOccurrences());
		result.addAll(repRel.getOccurrences());
		result.addAll(undefFormal.getOccurrences());
		result.addAll(undefPhase.getOccurrences());
		result.addAll(wholeOver.getOccurrences());
		result.addAll(partOver.getOccurrences());
		result.addAll(decInt.getOccurrences());
		return result;
	}
	
	public PartOverAntipattern getPartOver() {
		return partOver;
	}
	public void setPartOver(PartOverAntipattern partOver) {
		this.partOver = partOver;
	}
	public DecIntAntipattern getDecInt() {
		return decInt;
	}
	public void setDecInt(DecIntAntipattern decInt) {
		this.decInt = decInt;
	}
	public void setAssCyc(AssCycAntipattern assCyc) {
		this.assCyc = assCyc;
	}
	
	public BinOverAntipattern getBinOver() {
		return binOver;
	}
	public void setBinOver(BinOverAntipattern binOver) {
		this.binOver = binOver;
	}
	public DepPhaseAntipattern getDepPhase() {
		return depPhase;
	}
	public void setDepPhase(DepPhaseAntipattern depPhase) {
		this.depPhase = depPhase;
	}
	public FreeRoleAntipattern getFreeRole() {
		return freeRole;
	}
	public void setFreeRole(FreeRoleAntipattern freeRole) {
		this.freeRole = freeRole;
	}
	public GSRigAntipattern getGsRig() {
		return gsRig;
	}
	public void setGsRig(GSRigAntipattern gsRig) {
		this.gsRig = gsRig;
	}
	public HetCollAntipattern getHetColl() {
		return hetColl;
	}
	public void setHetColl(HetCollAntipattern hetColl) {
		this.hetColl = hetColl;
	}
	public HomoFuncAntipattern getHomoFunc() {
		return homoFunc;
	}
	public void setHomoFunc(HomoFuncAntipattern homoFunc) {
		this.homoFunc = homoFunc;
	}
	public ImpAbsAntipattern getImpAbs() {
		return impAbs;
	}
	public void setImpAbs(ImpAbsAntipattern impAbs) {
		this.impAbs = impAbs;
	}
	
	public MixIdenAntipattern getMixIden() {
		return mixIden;
	}
	public void setMixIden(MixIdenAntipattern mixIden) {
		this.mixIden = mixIden;
	}
	public MixRigAntipattern getMixRig() {
		return mixRig;
	}
	public void setMixRig(MixRigAntipattern mixRig) {
		this.mixRig = mixRig;
	}
	public MultiDepAntipattern getMultiDep() {
		return multiDep;
	}
	public void setMultiDep(MultiDepAntipattern multiDep) {
		this.multiDep = multiDep;
	}
	public RelCompAntipattern getRelComp() {
		return relComp;
	}
	public void setRelComp(RelCompAntipattern relComp) {
		this.relComp = relComp;
	}
	public RelOverAntipattern getRelOver() {
		return relOver;
	}
	public void setRelOver(RelOverAntipattern relOver) {
		this.relOver = relOver;
	}
	public RelRigAntipattern getRelRig() {
		return relRig;
	}
	public void setRelRig(RelRigAntipattern relRig) {
		this.relRig = relRig;
	}
	public RelSpecAntipattern getRelSpec() {
		return relSpec;
	}
	public void setRelSpec(RelSpecAntipattern relSpec) {
		this.relSpec = relSpec;
	}
	public RepRelAntipattern getRepRel() {
		return repRel;
	}
	public void setRepRel(RepRelAntipattern repRel) {
		this.repRel = repRel;
	}
	public UndefFormalAntipattern getUndefFormal() {
		return undefFormal;
	}
	public void setUndefFormal(UndefFormalAntipattern undefFormal) {
		this.undefFormal = undefFormal;
	}
	public UndefPhaseAntipattern getUndefPhase() {
		return undefPhase;
	}
	public void setUndefPhase(UndefPhaseAntipattern undefPhase) {
		this.undefPhase = undefPhase;
	}
	public WholeOverAntipattern getWholeOver() {
		return wholeOver;
	}
	public void setWholeOver(WholeOverAntipattern wholeOver) {
		this.wholeOver = wholeOver;
	}
	
	
	
}
