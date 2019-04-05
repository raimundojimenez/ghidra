/* ###
 * IP: GHIDRA
 * REVIEWED: YES
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package docking;

import ghidra.util.HelpLocation;

import javax.swing.Icon;

import resources.ResourceManager;
import docking.action.DockingAction;
import docking.action.MenuData;

class ShowWindowAction extends DockingAction implements Comparable<ShowWindowAction> {

	private static final Icon ICON = ResourceManager.loadImage("images/application_xp.png");
	private static final String MENU_WINDOW = "&" + DockingWindowManager.COMPONENT_MENU_NAME;

	private static final int MAX_LENGTH = 40;

	private final DetachedWindowNode node;

	private static String truncateTitleAsNeeded(String title) {
		if (title.length() <= MAX_LENGTH) {
			return title;
		}

		return title.substring(0, MAX_LENGTH - 3) + "...";
	}

	ShowWindowAction(DetachedWindowNode node) {
		super(truncateTitleAsNeeded(node.getTitle()), DockingWindowManager.DOCKING_WINDOWS_OWNER,
			false);
		this.node = node;

		setMenuBarData(new MenuData(new String[] { MENU_WINDOW, getName() }, ICON, "WindowGroup"));
		setHelpLocation(new HelpLocation("Tool", "DefaultTools")); // somewhat arbitrary location
	}

	@Override
	public void actionPerformed(ActionContext context) {
		DockingWindowManager manager = DockingWindowManager.getActiveInstance();
		manager.toFront(node.getWindow());
	}

	@Override
	public int compareTo(ShowWindowAction o) {
		return getName().compareToIgnoreCase(o.getName());
	}
}
