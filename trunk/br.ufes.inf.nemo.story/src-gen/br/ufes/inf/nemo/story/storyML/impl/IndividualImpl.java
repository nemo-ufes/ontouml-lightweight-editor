/**
 */
package br.ufes.inf.nemo.story.storyML.impl;

import br.ufes.inf.nemo.story.storyML.Individual;
import br.ufes.inf.nemo.story.storyML.StoryMLPackage;
import br.ufes.inf.nemo.story.storyML.World;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Individual</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.story.storyML.impl.IndividualImpl#getPresent_in <em>Present in</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.story.storyML.impl.IndividualImpl#getAbsent_from <em>Absent from</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IndividualImpl extends Story_elementImpl implements Individual
{
  /**
   * The cached value of the '{@link #getPresent_in() <em>Present in</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPresent_in()
   * @generated
   * @ordered
   */
  protected EList<World> present_in;

  /**
   * The cached value of the '{@link #getAbsent_from() <em>Absent from</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAbsent_from()
   * @generated
   * @ordered
   */
  protected EList<World> absent_from;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected IndividualImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return StoryMLPackage.Literals.INDIVIDUAL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<World> getPresent_in()
  {
    if (present_in == null)
    {
      present_in = new EObjectResolvingEList<World>(World.class, this, StoryMLPackage.INDIVIDUAL__PRESENT_IN);
    }
    return present_in;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<World> getAbsent_from()
  {
    if (absent_from == null)
    {
      absent_from = new EObjectResolvingEList<World>(World.class, this, StoryMLPackage.INDIVIDUAL__ABSENT_FROM);
    }
    return absent_from;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case StoryMLPackage.INDIVIDUAL__PRESENT_IN:
        return getPresent_in();
      case StoryMLPackage.INDIVIDUAL__ABSENT_FROM:
        return getAbsent_from();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case StoryMLPackage.INDIVIDUAL__PRESENT_IN:
        getPresent_in().clear();
        getPresent_in().addAll((Collection<? extends World>)newValue);
        return;
      case StoryMLPackage.INDIVIDUAL__ABSENT_FROM:
        getAbsent_from().clear();
        getAbsent_from().addAll((Collection<? extends World>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case StoryMLPackage.INDIVIDUAL__PRESENT_IN:
        getPresent_in().clear();
        return;
      case StoryMLPackage.INDIVIDUAL__ABSENT_FROM:
        getAbsent_from().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case StoryMLPackage.INDIVIDUAL__PRESENT_IN:
        return present_in != null && !present_in.isEmpty();
      case StoryMLPackage.INDIVIDUAL__ABSENT_FROM:
        return absent_from != null && !absent_from.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //IndividualImpl
