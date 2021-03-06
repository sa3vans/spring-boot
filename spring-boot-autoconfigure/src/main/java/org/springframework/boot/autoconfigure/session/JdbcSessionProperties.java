/*
 * Copyright 2012-2017 the original author or authors.
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

package org.springframework.boot.autoconfigure.session;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for JDBC backed Spring Session.
 *
 * @author Vedran Pavic
 * @since 2.0.0
 */
@ConfigurationProperties(prefix = "spring.session.jdbc")
public class JdbcSessionProperties {

	private static final String DEFAULT_SCHEMA_LOCATION = "classpath:org/springframework/"
			+ "session/jdbc/schema-@@platform@@.sql";

	private static final String DEFAULT_TABLE_NAME = "SPRING_SESSION";

	/**
	 * Path to the SQL file to use to initialize the database schema.
	 */
	private String schema = DEFAULT_SCHEMA_LOCATION;

	/**
	 * Name of database table used to store sessions.
	 */
	private String tableName = DEFAULT_TABLE_NAME;

	private final Initializer initializer = new Initializer();

	public String getSchema() {
		return this.schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Initializer getInitializer() {
		return this.initializer;
	}

	public class Initializer {

		/**
		 * Create the required session tables on startup if necessary. Enabled
		 * automatically if the default table name is set or a custom schema is
		 * configured.
		 */
		private Boolean enabled;

		public boolean isEnabled() {
			if (this.enabled != null) {
				return this.enabled;
			}
			boolean defaultTableName = DEFAULT_TABLE_NAME.equals(getTableName());
			boolean customSchema = !DEFAULT_SCHEMA_LOCATION.equals(getSchema());
			return (defaultTableName || customSchema);
		}

		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}

	}

}
