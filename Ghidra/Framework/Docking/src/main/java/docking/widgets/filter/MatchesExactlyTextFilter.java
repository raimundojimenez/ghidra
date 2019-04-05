/* ###
 * IP: GHIDRA
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
package docking.widgets.filter;

import java.util.regex.Pattern;

import ghidra.util.UserSearchUtils;

/**
 * A filter that will pass text when it matches exactly.
 */
public class MatchesExactlyTextFilter extends MatchesPatternTextFilter {

	private boolean caseSensitive;
	private boolean allowGlobbing;

	public MatchesExactlyTextFilter(String filterText, boolean caseSensitive,
			boolean allowGlobbing) {
		super(filterText);
		this.caseSensitive = caseSensitive;
		this.allowGlobbing = allowGlobbing;
	}

	@Override
	protected Pattern createPattern() {
		int options = Pattern.DOTALL;
		if (!caseSensitive) {
			options |= Pattern.CASE_INSENSITIVE;
		}

		Pattern pattern = UserSearchUtils.createPattern(filterText, allowGlobbing, options);
		return pattern;
	}

	@Override
	public boolean isSubFilterOf(TextFilter filter) {
		// we matched exactly--there can be no further sub-filter
		return false;
	}

}
