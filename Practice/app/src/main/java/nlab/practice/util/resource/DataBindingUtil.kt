@file:JvmName("DataBindingUtil")
package nlab.practice.util.resource

import nlab.practice.util.databinding.LiveEvent

/**
 * @author Doohyun
 */

/**
 * LiveEvent caller
 *
 * @param e
 */
fun callEvent(e : LiveEvent<*>) = e.call()