/**
 * Copyright (C) 2015 The Gravitee team (http://gravitee.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.gravitee.policy.requestvalidation.validator;

import java.util.regex.Pattern;

/**
 * @author David BRASSELY (david.brassely at graviteesource.com)
 * @author GraviteeSource Team
 */
public class PatternConstraintValidator extends StringConstraintValidator {

    private boolean init = false;

    private Pattern pattern;

    @Override
    public void initialize(String ... parameters) {
        try {
            if (parameters != null && parameters.length > 0) {
                pattern = Pattern.compile(parameters[0]);
                init = true;
            }
        } catch (Throwable t) {
        }
    }

    @Override
    public boolean isValid(String value) {
    	if(value == null) {
    		return false;
    	}
        return init && pattern.matcher(value).find();
    }

    @Override
    public String getMessageTemplate() {
        return "'%s' is not valid (pattern: '%s')";
    }
}
